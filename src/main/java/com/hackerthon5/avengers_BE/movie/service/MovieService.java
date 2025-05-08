package com.hackerthon5.avengers_BE.movie.service;

import com.hackerthon5.avengers_BE.movie.dto.MovieDetailResponse;
import com.hackerthon5.avengers_BE.movie.dto.MovieResponseDTO;

public interface MovieService {
    MovieResponseDTO getMovies();
    MovieDetailResponse getMovie(Long movieId);
}
