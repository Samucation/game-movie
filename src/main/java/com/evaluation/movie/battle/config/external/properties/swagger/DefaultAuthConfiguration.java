package com.evaluation.movie.battle.config.external.properties.swagger;

import lombok.Getter;

@Getter
public class DefaultAuthConfiguration {

    private String authorizationScope;
    private String authorizationDescription;
    private String securityReference;
    private String enable;

    public DefaultAuthConfiguration(String authorizationScope, String authorizationDescription, String securityReference, String enable) {
        this.authorizationScope = authorizationScope;
        this.authorizationDescription = authorizationDescription;
        this.securityReference = securityReference;
        this.enable = enable;
    }

}
