package com.evaluation.movie.battle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
public class MovieStatusDTO {

    private List<String> repeatedMovies;
    private Boolean isFailValidation;

}
