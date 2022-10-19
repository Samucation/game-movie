package com.evaluation.movie.battle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @JsonProperty("Source")
    public String source;
    @JsonProperty("Value")
    public String value;

}
