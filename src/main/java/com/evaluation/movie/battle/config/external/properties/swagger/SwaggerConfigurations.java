package com.evaluation.movie.battle.config.external.properties.swagger;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "app.api.swagger")
public class SwaggerConfigurations {

    private String basePackage;
    private SwaggerAuthConfiguration swaggerAuth;

    public SwaggerConfigurations(String basePackage, SwaggerAuthConfiguration swaggerAuth) {
        this.basePackage = basePackage;
        this.swaggerAuth = swaggerAuth;
    }

}
