package com.evaluation.movie.battle.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameStartDTO {

    private UserDTO userDTO;
    private List<String> movieTitleList;

}
