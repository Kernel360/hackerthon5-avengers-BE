package com.hackerthon5.avengers_BE.review.service;
import com.hackerthon5.avengers_BE.review.domain.Review;
import java.util.List;

public interface ReviewService {
    public Review createReview(Review review);

    Review updateReview(Review review);

    void deleteReview(long reviewId);

    List<Review> getMovieReview(long movieId);

    List<Review> getMyReview(long memberId);

}
