package com.evaluation.movie.battle.dto;

import com.evaluation.movie.battle.model.Rating;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OmdbMovieDTO {

    public String title;
    public String year;
    public String rated;
    public String released;
    public String runtime;
    public String genre;
    public String director;
    public String writer;
    public String actors;
    public String plot;
    public String language;
    public String country;
    public String awards;
    public String poster;
    public ArrayList<Rating> ratings;
    public String metascore;
    public String imdbRating;
    public String imdbVotes;
    public String imdbID;
    public String type;
    public String dVD;
    public String boxOffice;
    public String production;
    public String website;
    public String response;

}
