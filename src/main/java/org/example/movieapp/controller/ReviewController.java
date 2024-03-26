package org.example.movieapp.controller;

import org.example.movieapp.model.Review;
import org.example.movieapp.service.ReviewService;
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

    @GetMapping("/reviews/{movieId}")
    public String showReviews(@PathVariable("movieId") Long movieId, Model model) {
        System.out.printf("show review ==> ");
        List<Review> reviews = reviewService.getReviewsByMovieId(movieId);
        model.addAttribute("reviews", reviews);
        System.out.println(reviewService.getReviewsByMovieId(movieId));
        return "movie_review";
    }

    @PostMapping("/reviews/{movieId}/add")
    public String addReview(@PathVariable("movieId") Long movieId, @ModelAttribute("review") Review review) {
        System.out.printf("add review");
        reviewService.addReview(movieId, review);
        return "redirect:/reviews/" + review.getMovieId();
    }
}
