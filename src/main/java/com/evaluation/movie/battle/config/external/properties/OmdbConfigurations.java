package com.evaluation.movie.battle.config.external.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "app.api.obdb-configuration")
public class OmdbConfigurations {

    private String requestUrl;
    private String tokenKey;

    public OmdbConfigurations(String requestUrl, String tokenKey){
        this.requestUrl = requestUrl;
        this.tokenKey = tokenKey;
    }

}
