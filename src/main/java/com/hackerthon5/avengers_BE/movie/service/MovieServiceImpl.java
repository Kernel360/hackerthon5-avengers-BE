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
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public MovieResponseDTO getMovies() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieDTO> movieDTOS = movies.stream()
                .map(movie -> {
                    List<Review> reviews = reviewRepository.findByMovieId(movie.getId());
                    double avgRating = 0;
                    if (!reviews.isEmpty()) {
                        avgRating = reviews.stream()
                                .mapToDouble(Review::getMemberRate)
                                .average()
                                .orElse(0);
                    }
                    return new MovieDTO(
                            movie.getTmdbId(),
                            movie.getId(),
                            movie.getTitle(),
                            movie.getDescription(),
                            movie.getVote_average(),
                            avgRating, // 평균 리뷰 점수 추가
                            movie.getPoster_path(),
                            movie.getRelease_date(),
                            List.of(),
                            movie.getGenre()
                    );
                })
                .toList();

        return new MovieResponseDTO(movieDTOS);
    }

    @Override
    public MovieDetailResponse getMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new CustomException(ApiExceptionCode.MOVIE_NOT_FOUND));

        List<Review> reviews = reviewRepository.findByMovieId(movieId);

        double rating = 0;
        for (Review review : reviews) {
            rating += review.getMemberRate();
        }

        MovieDTO movieDTO = new MovieDTO(
                movie.getTmdbId(),
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getVote_average(),
                rating/reviews.size(),
                movie.getPoster_path(),
                movie.getRelease_date(),
                List.of(),
                movie.getGenre()
        );

        MovieDetailResponse response = new MovieDetailResponse(movieDTO, reviews);

        return response;
    }
}
