package org.example.movieapp.controller;

import org.example.movieapp.model.Movie;
import org.example.movieapp.model.Review;
import org.example.movieapp.service.MovieService;
import org.example.movieapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/movies")
    public String showMovieList(Model model) {
//        System.out.printf("show movies list ==> ");
        model.addAttribute("movies", movieService.getAllMovies());
//        System.out.println(movieService.getAllMovies());
        return "movie_list";
    }

    @GetMapping("/movies/{id}")
    public String showMovieDetails(@PathVariable("id") Long id, Model model) {
//        System.out.printf("show exact movie by id ==> ");
        Movie movie = movieService.getMovieById(id);
        List<Review> reviews = reviewService.getReviewsByMovieId(id);
        if (movie == null) {
            return "redirect:/movies";
        }
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
//        System.out.println(movieService.getMovieById(id));
        return "movie_details";
    }

    @PostMapping("/movies/{id}")
    public String addReview(@PathVariable("id") Long movieId, Review review) {
        reviewService.addReview(movieId, review);
        return "redirect:/movies/"+movieId;
    }
}
