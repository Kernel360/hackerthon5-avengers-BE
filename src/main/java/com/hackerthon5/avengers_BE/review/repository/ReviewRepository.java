package com.hackerthon5.avengers_BE.review.repository;

import com.hackerthon5.avengers_BE.movie.domain.Movie;
import com.hackerthon5.avengers_BE.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovieId(long movieId);
    List<Review> findByMemberId(long memberId);
    @Query("SELECT r FROM Review r WHERE r.movieId IN :movieIds")
    List<Review> findAllByMovieIds(@Param("movieIds") List<Long> movieIds);
}
