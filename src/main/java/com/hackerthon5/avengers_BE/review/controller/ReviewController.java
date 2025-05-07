package com.hackerthon5.avengers_BE.review.controller;

import com.hackerthon5.avengers_BE.review.domain.Review;
import com.hackerthon5.avengers_BE.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/test")
    public String testReview(){ return "Test SUCCESS"; }

    @PostMapping("/createReview")
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        Review newReview = reviewService.createReview(review);

        return ResponseEntity.ok(newReview);
    }

    @PostMapping("/updateReview")
    public ResponseEntity<Review> updateReview(@RequestBody Review review){
        
        return ResponseEntity.ok(review);
    }





}
