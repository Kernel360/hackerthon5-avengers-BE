package com.hackerthon5.avengers_BE.review.repository;

import com.hackerthon5.avengers_BE.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ReviewRepository extends JpaRepository<Review, Long> {

}
