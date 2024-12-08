package com.cinemacrit.platform.controllers;

import com.cinemacrit.platform.models.Review;
import com.cinemacrit.platform.repositories.ReviewRepository;
import com.cinemacrit.platform.services.ReviewService;
import com.cinemacrit.platform.services.UserDetailsPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * Controller class for managing reviews associated with movies.
 * Handles operations for adding new reviews and deleting existing reviews.
 */
@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;

    /**
     * Default constructor.
     * This constructor is automatically provided by Spring Framework for dependency injection.
     */
    public ReviewController() {}

    /**
     * Handles the submission of a new review for a specific movie.
     * Retrieves the current user's details, sets the review properties such as
     * movie ID, creation date, and username, and saves the review to the repository.
     *
     * @param movieId the ID of the movie to which the review is associated
     * @param review the review object containing the review details
     * @return a redirect URL to the movie's page after the review is added
     */
    @PostMapping("/{id}")
    public String addReview(@PathVariable(name = "id") String movieId, Review review) {
        UserDetailsPrincipal userDetailsPrincipal = (UserDetailsPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long movieIdLong = Long.parseLong(movieId);
        review.setId(null);
        review.setCreatedDate(LocalDateTime.now());
        review.setMovieId(movieIdLong);
        review.setUsername(userDetailsPrincipal.getUsername());

        reviewRepository.save(review);
        return "redirect:/movies/?id="+movieId;
    }

    /**
     * Handles the removal of an existing review for a specific movie.
     * Deletes the review identified by its ID and then redirects to the movie's page.
     *
     * @param reviewId the ID of the review to be deleted
     * @param movieId the ID of the movie associated with the review
     * @return a redirect URL to the movie's page after the review is removed
     */
    @PostMapping("/delete")
    public String removeMovie(@RequestParam("reviewId") Long reviewId, @RequestParam("movieId") Long movieId) {
        reviewRepository.deleteById(reviewId);
        return "redirect:/movies/?id="+movieId;
    }
}
