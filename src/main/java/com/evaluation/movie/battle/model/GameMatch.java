package com.evaluation.movie.battle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_game_match", schema = "game_movie")
public class GameMatch {

    @Id
    @Column(name = "id_game_match", nullable = false)
    private Long id;

    @Column(name="nr_game_score")
    private Integer gameScore;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameMatch")
    private Set<Movie> moviesNames;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name="sg_match_ended")
    private Boolean matchEnded;

    @Column(name="dt_create")
    private Date createDate;

    @Column(name="dt_updated")
    private Date updatedDate;

}
