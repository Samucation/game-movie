package com.evaluation.movie.battle.config.external.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "app.api.game-match-configuration")
public class GameMathConfiguration {

    private String numberOfAttempts;

    public GameMathConfiguration(String numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }
}
