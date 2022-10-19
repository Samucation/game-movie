package com.evaluation.movie.battle.service;

import com.evaluation.movie.battle.config.external.properties.OmdbConfigurations;
import com.evaluation.movie.battle.model.OmdbMovie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Component
public class OmdbRequestExecutor {

    private final OmdbConfigurations omdbConfigurations;
    private final RestTemplate restTemplate;

    public OmdbMovie getMovie(String apiKey, String title) {
        String url = omdbConfigurations.getRequestUrl();
        return restTemplate.postForObject(String.format(url, apiKey, title), null, OmdbMovie.class);
    }

}
