package com.evaluation.movie.battle.service;

import com.evaluation.movie.battle.config.external.properties.OmdbConfigurations;
import com.evaluation.movie.battle.dto.OmdbMovieDTO;
import com.evaluation.movie.battle.mapper.OmdbMovieMapper;
import com.evaluation.movie.battle.model.OmdbMovie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Component
public class OmdbRequestExecutor {

    private final OmdbConfigurations omdbConfigurations;
    private final RestTemplate restTemplate;
    private final OmdbMovieMapper omdbMovieMapper;

    public OmdbMovieDTO getMovie(String movileTitle) {
        String url = omdbConfigurations.getRequestUrl();
        String tokenKey = omdbConfigurations.getTokenKey();
        OmdbMovieDTO omdbMovieDTO = omdbMovieMapper.convert(
                restTemplate.postForObject(
                        String.format(url, tokenKey, movileTitle), null, OmdbMovie.class));
        return omdbMovieDTO;
    }

}
