package com.evaluation.movie.battle.service;

import com.evaluation.movie.battle.builder.UserMapper;
import com.evaluation.movie.battle.config.external.properties.GameMathConfiguration;
import com.evaluation.movie.battle.dto.*;
import com.evaluation.movie.battle.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class GameMatchService {

    private final UserMapper userMapper;
    private GameService gameService;
    private final GameMathConfiguration gameMathConfiguration;


    public GameStatusDTO startGame(UserDTO userDTO, List<String> movieGameNameList, String hiScoreMovieName) {
        UserDTO currentUserDB = gameService.findUser(userDTO);
        MovieStatusDTO movieStatusDTO = gameService.showRepeatedMoviesIfExist(movieGameNameList);
        GameStatusDTO gameStatusDTO = new GameStatusDTO();
        GameMatchDTO gameMatchDTO = new GameMatchDTO();
        List<GameMatchDTO> gameMatchDTOIntoDataBase = gameService.findActiveGameMatchByUser(currentUserDB);

        Integer activeGameMatchers = gameService.countGameMatcherActivesByUser(currentUserDB);
        Integer currentGameStage = activeGameMatchers++;
        Integer numberAttemptsAllowed = Integer.parseInt(gameMathConfiguration.getNumberOfAttempts());

        if ((currentGameStage) > (numberAttemptsAllowed)) {
            gameStatusDTO.setGameStatus("O número de estagios atuais excede o permitido pelo jogo! número de estagios atual: " + currentGameStage +
                    " número permitido: " + numberAttemptsAllowed + " Encerre o jogo para iniciar uma nova partida.");
            gameStatusDTO.setGameMatchDTOList(gameMatchDTOIntoDataBase);
            return gameStatusDTO;
        }

        if (movieStatusDTO.getIsFailValidation()) {
            gameStatusDTO.setGameStatus("Existem filmes repetidos, tente novammente, filme repetido: " + movieStatusDTO.getRepeatedMovies());
            return gameStatusDTO;
        }

        gameStatusDTO.setGameStatus("Stagio atual do jogo: " + currentGameStage + " número máximo de stagios atuais configurados: " + numberAttemptsAllowed);
        gameMatchDTO.setGameStage(currentGameStage);
        gameMatchDTOIntoDataBase.add(gameMatchDTO);

        gameMatchDTO.setMoviesNames(gameMovieStageToGameMovieDB(movieGameNameList));
        gameMatchDTO.setUser(userMapper.convert(currentUserDB));
        gameMatchDTO.setMatchEnded(Boolean.FALSE);
        OmdbMovieDTO omdbMovieDTO = null;

        try {
            omdbMovieDTO = gameService.getMovieWithTheHighestScore(movieGameNameList);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(omdbMovieDTO.getTitle().equals(hiScoreMovieName)){
            gameMatchDTO.setGameScore(1);
        }

        if (Objects.isNull(gameMatchDTO.getCreateDate())) {
            gameMatchDTO.setCreateDate(new Date());
        } else {
            gameMatchDTO.setUpdatedDate(new Date());
        }
        gameService.saveGameMath(gameMatchDTO);
        return gameStatusDTO;
    }

    private List<Movie> gameMovieStageToGameMovieDB(List<String> movieGameNameList){
        List<Movie> movieList = new ArrayList<>();
        for(String movieGameName: movieGameNameList){
            Movie movieMathDB = new Movie();
            movieMathDB.setName(movieGameName);
            movieList.add(movieMathDB);
        }
        return movieList;
    }

    public GameStatusDTO showGameResult(UserDTO userDTO) {
        UserDTO currentUserDB = gameService.findUser(userDTO);
        Integer activeGameMatchers = gameService.countGameMatcherActivesByUser(currentUserDB);
        Integer numberAttemptsAllowed = Integer.parseInt(gameMathConfiguration.getNumberOfAttempts());
        GameStatusDTO gameStatusDTO = new GameStatusDTO();

        if (activeGameMatchers < numberAttemptsAllowed) {
            gameStatusDTO.setGameStatus("O jogo ainda não chegou ao fim! continue jogando até chegar ao fim do jogo! estagio atual: " + activeGameMatchers + " " +
                                        "de estagios necessários para encerrar a partida: " + numberAttemptsAllowed);
            return gameStatusDTO;
        }

        if (activeGameMatchers == numberAttemptsAllowed) {
            gameStatusDTO.setGameStatus("O jogo chegou ao fim! os resultados podem ser conferidos a seguir!");
            gameStatusDTO.setScore(gameService.sumScoresByAllActiveGameMatches(currentUserDB));
            return gameStatusDTO;
        }
        return gameStatusDTO;
    }

    public GameStatusDTO endGame(UserDTO userDTO) {
        UserDTO currentUserDB = gameService.findUser(userDTO);
        List<GameMatchDTO> gameMatchDTOList = gameService.findActiveGameMatchByUser(currentUserDB);
        gameService.deleteAllActivesGameMatches(gameMatchDTOList);
        return new GameStatusDTO("O jogo foi encerrado antes de chegar ao fim! todos os pontos da partida atual foram perdidos.", null, 0);
    }

    public Integer showHiScoreByUser(UserDTO userDTO) {
        return gameService.sumHiScoreByUser(userDTO);
    }

    //TODO Precisa ser feito
    public Integer showAllHiScores(UserDTO userDTO) {
        return gameService.sumHiScoreByUser(userDTO);
    }

}
