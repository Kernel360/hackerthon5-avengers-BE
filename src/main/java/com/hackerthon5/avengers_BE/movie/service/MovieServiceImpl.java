package com.hackerthon5.avengers_BE.movie.service;

import com.hackerthon5.avengers_BE.common.ApiExceptionCode;
import com.hackerthon5.avengers_BE.common.CustomException;
import com.hackerthon5.avengers_BE.movie.domain.Movie;
import com.hackerthon5.avengers_BE.movie.dto.MovieDTO;
import com.hackerthon5.avengers_BE.movie.dto.MovieDetailResponse;
import com.hackerthon5.avengers_BE.movie.dto.MovieResponseDTO;
import com.hackerthon5.avengers_BE.movie.repository.MovieRepository;
import com.hackerthon5.avengers_BE.review.domain.Review;
import com.hackerthon5.avengers_BE.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public MovieResponseDTO getMovies() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieDTO> movieDTOS = movies.stream()
                .map(movie -> new MovieDTO(
                        movie.getTmdbId(),
                        movie.getId(),
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

    @Override
    public MovieDetailResponse getMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new CustomException(ApiExceptionCode.MOVIE_NOT_FOUND));

        MovieDTO movieDTO = new MovieDTO(
                movie.getTmdbId(),
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getVote_average(),
                movie.getPoster_path(),
                movie.getRelease_date(),
                List.of(),
                movie.getGenre()
        );

        List<Review> reviews = reviewRepository.findByMovieId(movieId);

        MovieDetailResponse response = new MovieDetailResponse(movieDTO, reviews);

        return response;
    }
}
