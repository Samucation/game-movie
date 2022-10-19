package com.evaluation.movie.battle.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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

    private String name;
    private String discription;
    private BigDecimal score;

}
