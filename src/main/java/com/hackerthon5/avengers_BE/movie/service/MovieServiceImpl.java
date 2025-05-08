package com.hackerthon5.avengers_BE.movie.service;

import com.hackerthon5.avengers_BE.common.ApiExceptionCode;
import com.hackerthon5.avengers_BE.common.CustomException;
import com.hackerthon5.avengers_BE.movie.domain.Movie;
import com.hackerthon5.avengers_BE.movie.dto.*;
import com.hackerthon5.avengers_BE.movie.repository.MovieRepository;
import com.hackerthon5.avengers_BE.review.domain.Review;
import com.hackerthon5.avengers_BE.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public MovieResponseDTO getMovies(Pageable pageable, SearchDTO searchDTO) {
        String searchType = Optional.ofNullable(searchDTO.searchType()).orElse("all");
        String keyword = Optional.ofNullable(searchDTO.keyword()).orElse("");

        Page<Movie> movies = switch(searchType){
            case "title" -> movieRepository.findByTitleContaining(keyword, pageable);
            case "description" -> movieRepository.findByDescriptionContaining(keyword, pageable);
            case "all" -> movieRepository.findByTitleContainingOrDescriptionContaining(keyword, keyword, pageable);
            case "" -> movieRepository.findAll(pageable);
            default -> throw new CustomException(ApiExceptionCode.INVALID_SEARCH_TPYE);
        };

        List<MovieDTO> movieDTOS = process(movies.getContent());

        return new MovieResponseDTO(movieDTOS, movies.getNumber(), movies.getTotalPages(), movies.getTotalElements());
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

    @Override
    public MoiveTop10ResponseDTO getPopular() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieDTO> movieDTOS = process(movies).stream()
                .sorted(Comparator.comparingDouble(MovieDTO::vote_average).reversed())
                .limit(10)
                .toList();

        return new MoiveTop10ResponseDTO(movieDTOS);
    }

    private List<MovieDTO> process(List<Movie> movies){
        return movies.stream()
                .map(movie -> {
                    List<Review> reviews = reviewRepository.findByMovieId(movie.getId());
                    double avgRating = reviews.stream()
                            .mapToDouble(Review::getMemberRate)
                            .average()
                            .orElse(0);
                    return new MovieDTO(
                            movie.getTmdbId(),
                            movie.getId(),
                            movie.getTitle(),
                            movie.getDescription(),
                            movie.getVote_average(),
                            avgRating,
                            movie.getPoster_path(),
                            movie.getRelease_date(),
                            List.of(),
                            movie.getGenre()
                    );
                })
                .toList();
    }
}
