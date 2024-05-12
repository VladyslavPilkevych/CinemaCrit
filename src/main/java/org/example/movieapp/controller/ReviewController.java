/**
 * Controller class for handling movie reviews.
 */
package org.example.movieapp.controller;

import org.example.movieapp.factory.ReviewFactory;
import org.example.movieapp.model.Review;
import org.example.movieapp.service.impl.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    final private ReviewFactory reviewFactory = new ReviewFactory();

    /**
     * Display reviews for a specific movie.
     *
     * @param movieId ID of the movie to display reviews
     * @param model   Model to hold attributes for the view
     * @return The view name for the movie review page
     */
    @GetMapping("/reviews/{movieId}")
    public String showReviews(@PathVariable("movieId") Long movieId, Model model) {
        List<Review> reviews = reviewService.getReviewsByMovieId(movieId);
        model.addAttribute("reviews", reviews);
        return "movie_review";
    }

    /**
     * Add a review for a specific movie.
     *
     * @param movieId ID of the movie to add review
     * @param review  Review object containing review details
     * @return Redirect to the movie review page after adding review
     */
    @PostMapping("/reviews/{movieId}/add")
    public String addReview(@PathVariable("movieId") Long movieId, @ModelAttribute("review") Review review) {
        Review newReview = reviewFactory.createReview();
        newReview.setMovieId(movieId);
        newReview.setCreatedDate(review.getCreatedDate());
        newReview.setUsername(review.getUsername());
        newReview.setComment(review.getComment());
        newReview.setRating(review.getRating());
        reviewService.addReview(newReview);
        Review.serializeReview(review, "review.ser");
        return "redirect:/reviews/" + newReview.getMovieId();
    }
}
