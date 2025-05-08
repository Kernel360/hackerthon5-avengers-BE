package com.hackerthon5.avengers_BE.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieDTO (
        int id, //  TMDB 고유 id
        String title,
        String overview,
        double vote_average,
        String poster_path,
        LocalDate release_date,
        List<Integer> genre_ids,
        String genre
){ }
