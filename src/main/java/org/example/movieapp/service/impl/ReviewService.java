package org.example.movieapp.service.impl;

import org.example.movieapp.model.Review;
import org.example.movieapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;


    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByMovieId(Long movieId) {
        return reviewRepository.findAllByMovieId(movieId);
    }

    public Review addReview(Review review) {
//        System.out.printf("movieId ==> ");
//        System.out.println(movieId);
//        System.out.printf("review ==> ");
//        System.out.println(review);
//        review.setMovieId(movieId);
        return reviewRepository.save(review);
    }
}
