package org.example.movieapp.controller;

import org.example.movieapp.model.Movie;
import org.example.movieapp.model.Review;
import org.example.movieapp.repository.ReviewRepository;
import org.example.movieapp.service.MovieService;
import org.example.movieapp.service.ReviewService;
import org.example.movieapp.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/movies")
    public String showMovieList(@RequestParam(name = "filter", required = false) String filter, Model model) {
//        System.out.printf("show movies list ==> ");
//        model.addAttribute("movies", movieService.getAllMovies());
        List<Movie> movies;
        if ("date".equals(filter)) {
            movies = movieService.getMoviesFilteredByDate();
        } else if ("rate".equals(filter)) {
            movies = movieService.getMoviesFilteredByRate();
        } else if ("popularity".equals(filter)) {
            movies = movieService.getMoviesFilteredByPopularity();
        } else {
            movies = movieService.getAllMovies();
        }
        model.addAttribute("movies", movies);
//        System.out.println(movieService.getAllMovies());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            model.addAttribute("auth", authentication.getName());
//            System.out.printf(authentication.getName());
//        }
        return "movie_list";
    }

    @GetMapping("/movies/")
    public String showMovieDetails(@RequestParam(name="id", defaultValue = "") Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        List<Review> reviews = reviewService.getReviewsByMovieId(id).reversed();
        if (movie == null) {
            return "redirect:/movies";
        }
//        System.out.println(reviews.get(0).getCreatedDate());
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
        model.addAttribute("review", new Review());
        model.addAttribute("dateUtils", new DateUtils());
        return "movie_details";
    }

    @PostMapping("/movies/{id}")
    public String addReview(@PathVariable(name = "id") String movieId, @RequestBody String body, Review review) {
        Long movieIdLong = Long.parseLong(movieId);
//        System.out.println(body);
//        reviewService.addReview(movieId, review);
        review.setId(null);
        review.setCreatedDate(LocalDateTime.now());
        review.setMovieId(movieIdLong);

        reviewRepository.save(review);
        return "redirect:/movies/?id="+movieId;
    }
}
