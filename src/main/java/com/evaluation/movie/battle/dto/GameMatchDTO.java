package com.evaluation.movie.battle.dto;

import com.evaluation.movie.battle.model.Movie;
import com.evaluation.movie.battle.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameMatchDTO {

    private Long id;

    private Integer gameScore;

    private List<Movie> moviesNames;

    private User user;

    private Boolean matchEnded;

    private Date createDate;

    private Date updatedDate;

}
