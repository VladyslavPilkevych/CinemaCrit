package org.example.movieapp.controller;

import org.example.movieapp.factory.ReviewFactory;
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

    final private ReviewFactory reviewFactory = new ReviewFactory();

    @GetMapping("/reviews/{movieId}")
    public String showReviews(@PathVariable("movieId") Long movieId, Model model) {
        List<Review> reviews = reviewService.getReviewsByMovieId(movieId);
        model.addAttribute("reviews", reviews);
        return "movie_review";
    }

    @PostMapping("/reviews/{movieId}/add")
    public String addReview(@PathVariable("movieId") Long movieId, @ModelAttribute("review") Review review) {
//        Movie newMovie = movieFactory.createMovie();
//        newMovie.setTitle(movie.getTitle());
//        newMovie.setDescription(movie.getDescription());
//        newMovie.setYear(movie.getYear());
//        movieService.addMovie(newMovie);
        Review newReview = reviewFactory.createReview();
        newReview.setMovieId(movieId);
        newReview.setCreatedDate(review.getCreatedDate());
        newReview.setUsername(review.getUsername());
        newReview.setComment(review.getComment());
        newReview.setRating(review.getRating());
        reviewService.addReview(newReview);
        return "redirect:/reviews/" + newReview.getMovieId();
    }
}
