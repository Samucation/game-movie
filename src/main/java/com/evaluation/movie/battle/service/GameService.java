package com.evaluation.movie.battle.service;

import com.evaluation.movie.battle.dto.OmdbMovieDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.*;

@AllArgsConstructor
@Service
public class GameService {

    private final Logger LOGGER = LogManager.getLogger(GameService.class);
    private final OmdbRequestExecutor omdbRequestExecutor;

    //TODO Ajustar esse método que não está funcionando corretamente.
    public <T> boolean existRepeatedMovie(List<String> movieTileList) {
        return movieTileList.stream().allMatch(new HashSet<>()::add);
    }

    public BigDecimal calculateGameRanking(OmdbMovieDTO omdbMovieDTO) throws ParseException {
        BigDecimal imdbVotes = stringValueToBigDecimal(omdbMovieDTO.getImdbVotes());
        BigDecimal imdbRating = omdbMovieDTO.getImdbRating();
        BigDecimal gameScoreForGame = imdbRating.multiply(imdbVotes);
        return gameScoreForGame;
    }

    private BigDecimal stringValueToBigDecimal(String numberValue) throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        BigDecimal parsedStringValue = (BigDecimal) decimalFormat.parse(numberValue);
        return parsedStringValue;
    }

    public OmdbMovieDTO getMovieWithTheHighestScore(List<String> movieTileList) throws ParseException {
        if(existRepeatedMovie(movieTileList)){
            LOGGER.debug("Filme repetido");
        }

        List<OmdbMovieDTO> omdbMovieDTOList = new ArrayList<>();
        for(String currentMovie: movieTileList){

            if(StringUtils.isNoneBlank(currentMovie)) {
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
