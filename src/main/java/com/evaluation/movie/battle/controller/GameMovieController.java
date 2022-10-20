package com.evaluation.movie.battle.controller;

import com.evaluation.movie.battle.config.external.properties.JwtConfigurations;
import com.evaluation.movie.battle.config.external.properties.OmdbConfigurations;
import com.evaluation.movie.battle.dto.GameStartDTO;
import com.evaluation.movie.battle.dto.OmdbMovieDTO;
import com.evaluation.movie.battle.service.GameService;
import com.evaluation.movie.battle.service.OmdbRequestExecutor;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@AllArgsConstructor
@RestController
public class GameMovieController {

    private final JwtConfigurations jwtConfigurations;
    private final OmdbConfigurations omdbConfigurations;
    private final OmdbRequestExecutor omdbRequestExecutor;
    private final GameService gameService;

    @GetMapping("get-jwt-config")
    public JwtConfigurations getJwtProperties(){
        return jwtConfigurations;
    }

    @GetMapping("get-omdb-request-config")
    public OmdbConfigurations getOmdbProperties(){
        return omdbConfigurations;
    }

    @GetMapping("get-omdb-movies")
    public OmdbMovieDTO getCloudMovies(@Param("title") String title){
        return omdbRequestExecutor.getMovie(title);
    }

    @GetMapping("get-omdb-movies-game")
    public OmdbMovieDTO getCloudMoviesGame(@Param("title") GameStartDTO gameStartDTO) throws ParseException {
        return gameService.getMovieWithTheHighestScore(gameStartDTO.getMovieTitleList());
    }

}
