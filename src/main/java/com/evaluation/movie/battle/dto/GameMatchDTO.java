package com.evaluation.movie.battle.dto;

import com.evaluation.movie.battle.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameMatchDTO {

    private Long id;

    private Integer gameScore;

    private Set<Movie> moviesNames;

    private Boolean matchEnded;

}
