package com.evaluation.movie.battle.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_movie", schema = "game_movie")
public class Movie {

    @Id
    @GeneratedValue
    @Column(name = "id_movie", nullable = false)
    private Long id;

    @Column(name="tx_name")
    private String name;

    @Column(name="tx_description")
    private String description;

    @Column(name="dt_create")
    private Date createDate;

    @Column(name="dt_updated")
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_match_id")
    private GameMatch gameMatch;


}
