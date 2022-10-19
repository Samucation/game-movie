package com.evaluation.movie.battle.controller;

import com.evaluation.movie.battle.config.external.properties.JwtConfigurations;
import com.evaluation.movie.battle.config.external.properties.OmdbConfigurations;
import com.evaluation.movie.battle.model.OmdbMovie;
import com.evaluation.movie.battle.service.OmdbRequestExecutor;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class GameMovieController {

    private final JwtConfigurations jwtConfigurations;
    private final OmdbConfigurations omdbConfigurations;
    private final OmdbRequestExecutor omdbRequestExecutor;

    @GetMapping("get-jwt-config")
    public JwtConfigurations getJwtProperties(){
        return jwtConfigurations;
    }

    @GetMapping("get-omdb-request-config")
    public OmdbConfigurations getOmdbProperties(){
        return omdbConfigurations;
    }

    @GetMapping("get-omdb-movies")
    public OmdbMovie getCloudMovies(@Param("apiKey") String apiKey, @Param("title") String title){
        return omdbRequestExecutor.getMovie(apiKey, title);
    }

}
