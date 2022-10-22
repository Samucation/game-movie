package com.evaluation.movie.battle.dto;

import com.evaluation.movie.battle.model.GameMatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private Integer hiGameScore;

    private String lastName;

    private String cpf;

    private String rg;

    private Integer age;

    private Integer ranking;

    private Date lastLogin;

    private Date createDate;

    private Date updatedDate;

    private List<GameMatch> gameMatchList;

}
