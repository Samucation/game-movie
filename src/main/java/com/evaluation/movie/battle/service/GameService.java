package com.evaluation.movie.battle.service;

import com.evaluation.movie.battle.builder.GameMathMapper;
import com.evaluation.movie.battle.builder.MovieGameMapper;
import com.evaluation.movie.battle.builder.UserMapper;
import com.evaluation.movie.battle.dto.*;
import com.evaluation.movie.battle.repository.GameMatchRepository;
import com.evaluation.movie.battle.repository.GameRepository;
import com.evaluation.movie.battle.repository.MovieRepository;
import com.evaluation.movie.battle.repository.UserRepository;
import com.evaluation.movie.battle.util.CalculateUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GameService {

    private final Logger LOGGER = LogManager.getLogger(GameService.class);
    private final OmdbRequestExecutor omdbRequestExecutor;
    private final GameRepository gameRepository;
    private final GameMatchRepository gameMatchRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final MovieGameMapper movieGameMapper;
    private final GameMathMapper gameMathMapper;
    private final UserMapper userMapper;
    private final CalculateUtils calculateUtils;

    public void saveGameMath(GameMatchDTO gameMatchDTO) {
        gameRepository.save(gameMathMapper.convert(gameMatchDTO));
    }

    public void saveMovies(MovieDTO movieDTO) {
        movieRepository.save(movieGameMapper.convert(movieDTO));
    }

    public List<UserDTO> showBestUserScore() {
        List<UserDTO> userDTOList = userMapper.convertDTOList(userRepository.listUserByHiScoreAsc());
        return userDTOList;
    }

    public UserDTO findUser(UserDTO userDTO){
        return userMapper.convert(userRepository.findUser(userMapper.convert(userDTO)));
    }

    public List<GameMatchDTO> findActiveGameMatchByUser(UserDTO userDTO) {
        return gameMathMapper.convertDTOList(gameMatchRepository.findActiveGameMatchByUser(userMapper.convert(userDTO)));
    }

    public Integer countGameMatcherActivesByUser(UserDTO userDTO) {
        return gameMatchRepository.countGameMatcherActivesByUser(userMapper.convert(userDTO));
    }

    public Integer sumScoresByAllActiveGameMatches(UserDTO userDTO) {
        return gameMatchRepository.sumScoresByAllActiveGameMatches(userMapper.convert(userDTO));
    }

    public Integer sumHiScoreByUser(UserDTO userDTO) {
        return gameMatchRepository.sumHiScoreByUser(userMapper.convert(userDTO));
    }

    public void deleteAllActivesGameMatches(List<GameMatchDTO> gameMatchDTOList) {
        gameMatchRepository.deleteAll(gameMathMapper.convertEntityList(gameMatchDTOList));
    }

    public MovieStatusDTO showRepeatedMoviesIfExist(List<String> movieTileList) {
        AtomicReference<Boolean> result = new AtomicReference<>();
        List<String> repeatedMovies = new ArrayList<>();
        Map<String, Long> moviesForQuantity = movieTileList.stream().collect(
                Collectors.groupingBy(item -> item.toUpperCase(Locale.ROOT), Collectors.counting()));

        moviesForQuantity.forEach((movieName, qtd) -> {
            if (qtd > 1) {
                repeatedMovies.add(movieName);
                result.set(Boolean.TRUE);
            }
        });
        return new MovieStatusDTO(repeatedMovies, result.get());
    }

    public BigDecimal calculateGameRanking(OmdbMovieDTO omdbMovieDTO) throws ParseException {
        BigDecimal imdbVotes = calculateUtils.removeNumberFormattingIssues(omdbMovieDTO.getImdbVotes());
        BigDecimal imdbRating = omdbMovieDTO.getImdbRating();
        BigDecimal gameScoreForGame = imdbRating.multiply(imdbVotes);
        return gameScoreForGame;
    }

    public OmdbMovieDTO getMovieWithTheHighestScore(List<String> movieTileList) throws ParseException {
        List<OmdbMovieDTO> omdbMovieDTOList = new ArrayList<>();
        for (String currentMovie : movieTileList) {
            if (StringUtils.isNoneBlank(currentMovie)) {
                OmdbMovieDTO omdbMovieDTO = omdbRequestExecutor.getMovie(currentMovie);
                omdbMovieDTO.setGameRanking(new BigDecimal(String.valueOf(calculateGameRanking(omdbMovieDTO))));
                omdbMovieDTOList.add(omdbMovieDTO);
                LOGGER.debug("Add movie to list, hiScoreCoparator: [{}], Score:[{}]", omdbMovieDTO.getTitle(), omdbMovieDTO.getGameRanking());
            }
        }
        Optional<OmdbMovieDTO> hiScoreMovie = omdbMovieDTOList.stream().max(Comparator.comparing(OmdbMovieDTO::getGameRanking));
        return hiScoreMovie.get();
    }

}
