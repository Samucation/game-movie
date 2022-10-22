package com.evaluation.movie.battle.builder;

import com.evaluation.movie.battle.dto.GameMatchDTO;
import com.evaluation.movie.battle.model.GameMatch;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMathMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public GameMatchDTO convert(GameMatch gameMatch){
        GameMatchDTO gameMatchDTO = modelMapper.map(gameMatch, GameMatchDTO.class);
        return gameMatchDTO;
    }

    public GameMatch convert(GameMatchDTO gameMatchDTO){
        GameMatch gameMatch = modelMapper.map(gameMatchDTO, GameMatch.class);
        return gameMatch;
    }

    public List<GameMatch> convertEntityList(List<GameMatchDTO> corporationDTOList){
        return corporationDTOList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<GameMatchDTO> convertDTOList(List<GameMatch> corporationList){
        return corporationList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
