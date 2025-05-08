package com.hackerthon5.avengers_BE.review.service;

import com.hackerthon5.avengers_BE.review.domain.Review;
import com.hackerthon5.avengers_BE.review.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public Review createReview(Review review){
        review.setPostDate(new Date());
        return reviewRepository.save(review);
    }

    public Review updateReview(Review reviewDTO){
        long pastReviewId = reviewDTO.getReviewId();

        Review pastReview = reviewRepository.findById(pastReviewId)
                .orElseThrow(() -> new EntityNotFoundException("review not found"));

        pastReview.setTitle(reviewDTO.getTitle());
        pastReview.setContent(reviewDTO.getContent());
        pastReview.setMemberRate(reviewDTO.getMemberRate());
        pastReview.setUpdateDate(new Date());

        return reviewRepository.save(pastReview);
    }

    @Override
    public void deleteReview(long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("review not found"));
        reviewRepository.delete(review);
    }

    @Override
    public List<Review> getMovieReview(long movieId) {
        List<Review> movieRList = reviewRepository.findByMovieId(movieId);
        return movieRList;
    }

    @Override
    public List<Review> getMyReview(long memberId) {
        List<Review> MyRList = reviewRepository.findByMemberId(memberId);
        return MyRList;
    }


}
