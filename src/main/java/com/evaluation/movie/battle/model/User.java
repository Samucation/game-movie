package com.evaluation.movie.battle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_user", schema = "game_movie")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id_user", nullable = false)
    private Long id;

    @Column(name="tx_name")
    private String name;

    @Column(name="nr_hi_game_score")
    private Integer hiGameScore;

    @Column(name="tx_lastname")
    private String lastName;

    @Column(name="tx_cpf")
    private String cpf;

    @Column(name="tx_rg")
    private String rg;

    @Column(name="sg_age")
    private Integer age;

    @Column(name="nr_ranking")
    private Integer ranking;

    @Column(name="dt_last_login")
    private Date lastLogin;

    @Column(name="dt_create")
    private Date createDate;

    @Column(name="dt_updated")
    private Date updatedDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<GameMatch> gameMatchList;

}
