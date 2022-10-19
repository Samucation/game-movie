package com.evaluation.movie.battle.config.external.properties.swagger;

import lombok.Getter;

@Getter
public class SwaggerAuthConfiguration {

    private ApiKeyConfiguration apiKey;
    private DefaultAuthConfiguration defaultAuth;

    public SwaggerAuthConfiguration(ApiKeyConfiguration apiKey, DefaultAuthConfiguration defaultAuth) {
        this.apiKey = apiKey;
        this.defaultAuth = defaultAuth;
    }
}
