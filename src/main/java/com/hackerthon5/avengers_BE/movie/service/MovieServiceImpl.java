package com.hackerthon5.avengers_BE.movie.service;

import com.hackerthon5.avengers_BE.movie.domain.Movie;
import com.hackerthon5.avengers_BE.movie.dto.MovieDTO;
import com.hackerthon5.avengers_BE.movie.dto.MovieResponseDTO;
import com.hackerthon5.avengers_BE.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Override
    public MovieResponseDTO getMovies() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieDTO> movieDTOS = movies.stream()
                .map(movie -> new MovieDTO(
                        movie.getTmdbId(),
                        movie.getTitle(),
                        movie.getDescription(),
                        movie.getVote_average(),
                        movie.getPoster_path(),
                        movie.getRelease_date(),
                        List.of(),
                        movie.getGenre()
                ))
                .toList();

        return new MovieResponseDTO(movieDTOS);
    }
}
