package com.evaluation.movie.battle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameStatusDTO {

    private String gameStatus;

    private GameMatchDTO gameMatchDTO;

}
