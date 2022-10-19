package com.evaluation.movie.battle.mapper;

import com.evaluation.movie.battle.dto.OmdbMovieDTO;
import com.evaluation.movie.battle.model.OmdbMovie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OmdbMovieMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public OmdbMovieDTO convert(OmdbMovie omdbMovie){
        OmdbMovieDTO omdbMovieDTO = modelMapper.map(omdbMovie, OmdbMovieDTO.class);
        return omdbMovieDTO;
    }

    public OmdbMovie convert(OmdbMovieDTO omdbMovieDTO){
        OmdbMovie omdbMovie = modelMapper.map(omdbMovieDTO, OmdbMovie.class);
        return omdbMovie;
    }

    public List<OmdbMovie> convertEntityList(List<OmdbMovieDTO> corporationDTOList){
        return corporationDTOList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<OmdbMovieDTO> convertDTOList(List<OmdbMovie> corporationList){
        return corporationList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
