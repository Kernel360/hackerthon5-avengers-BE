package com.hackerthon5.avengers_BE.review.service;

import com.hackerthon5.avengers_BE.review.domain.Review;
import com.hackerthon5.avengers_BE.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public Review createReview(Review review){
        review.setPostDate(new Date());
        return reviewRepository.save(review);
    }

}
