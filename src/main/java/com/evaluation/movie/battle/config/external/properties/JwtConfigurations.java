package com.evaluation.movie.battle.config.external.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "app.api.jwt-configuration")
public class JwtConfigurations {

    private String tokenPassword;
    private Integer tokenExpiration;
    private String[] whitelist;

    public JwtConfigurations(String tokenPassword, Integer tokenExpiration, String[] whitelist){
        this.tokenPassword = tokenPassword;
        this.tokenExpiration = tokenExpiration;
        this.whitelist = whitelist;
    }

}
