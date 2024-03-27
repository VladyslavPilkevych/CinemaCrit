package org.example.movieapp.service;

import org.example.movieapp.model.Review;
import org.example.movieapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByMovieId(Long movieId) {
        return reviewRepository.findAllByMovieId(movieId);
    }

    public void addReview(Long movieId, Review review) {
        System.out.printf("movieId ==> ");
        System.out.println(movieId);
        System.out.printf("review ==> ");
        System.out.println(review);
    }
}
