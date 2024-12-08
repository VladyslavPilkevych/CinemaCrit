package com.cinemacrit.platform.services;

import com.cinemacrit.platform.models.Review;
import com.cinemacrit.platform.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing review-related operations.
 * Provides methods to perform CRUD operations on reviews and retrieve reviews by movie ID.
 */
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Default constructor for ReviewService.
     * This constructor is auto-generated and initializes the ReviewService with required dependencies.
     */
    public ReviewService() {}

    /**
     * Retrieves all reviews from the repository.
     *
     * @return a list of all reviews.
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Retrieves all reviews associated with a specific movie by its ID.
     *
     * @param movieId the ID of the movie for which to retrieve reviews.
     * @return a list of reviews for the specified movie.
     */
    public List<Review> getReviewsByMovieId(Long movieId) {
        return reviewRepository.findAllByMovieId(movieId);
    }

    /**
     * Adds a new review to the repository.
     *
     * @param review the review to add.
     * @return the saved review.
     */
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}
