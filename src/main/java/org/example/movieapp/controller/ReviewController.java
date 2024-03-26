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
        List<Review> reviews = reviewService.getReviewsByMovieId(movieId);
        model.addAttribute("reviews", reviews);
        return "reviews_list";
    }

    @PostMapping("/reviews/add")
    public String addReview(@ModelAttribute("review") Review review) {
        reviewService.addReview(review);
        return "redirect:/reviews/" + review.getMovieId();
    }
}
