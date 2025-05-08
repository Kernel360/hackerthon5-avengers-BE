package com.hackerthon5.avengers_BE.review.repository;

import com.hackerthon5.avengers_BE.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {
<<<<<<< HEAD
    List<Review> findByMovieId(long movieId);
    List<Review> findByMemberId(long memberId);
=======

    List<Review> findByMovieId(Long movieId);
>>>>>>> 3d43e3fe3af2d222ef39541b4182af54fd5ca268
}
