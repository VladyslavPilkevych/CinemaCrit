/**
 * Service class for review-related operations.
 */
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

    /**
     * Retrieves all reviews.
     *
     * @return a list of all reviews
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Retrieves reviews by movie ID.
     *
     * @param movieId the ID of the movie
     * @return a list of reviews for the specified movie ID
     */
    public List<Review> getReviewsByMovieId(Long movieId) {
        return reviewRepository.findAllByMovieId(movieId);
    }

    /**
     * Adds a review.
     *
     * @param review the review to add
     * @return the added review
     */
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}
