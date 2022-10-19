package com.evaluation.movie.battle.config.external.properties.swagger;

import lombok.Getter;

@Getter
public class ApiKeyConfiguration {

    private String name;
    private String keyName;
    private String pass;

    public ApiKeyConfiguration(String name, String keyName, String pass) {
        this.name = name;
        this.keyName = keyName;
        this.pass = pass;
    }

}
