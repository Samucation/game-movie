package com.evaluation.movie.battle.mapper;

import com.evaluation.movie.battle.dto.MovieDTO;
import com.evaluation.movie.battle.model.Movie;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MovieGameMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public MovieDTO convert(Movie movie){
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
        return movieDTO;
    }

    public Movie convert(MovieDTO movieDTO){
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        return movie;
    }

    public List<Movie> convertEntityList(List<MovieDTO> corporationDTOList){
        return corporationDTOList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> convertDTOList(List<Movie> corporationList){
        return corporationList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
