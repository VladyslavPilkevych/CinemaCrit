package org.example.movieapp.controller;

import org.example.movieapp.model.Movie;
import org.example.movieapp.repository.MovieRepository;
import org.example.movieapp.service.AuthenticationService;
import org.example.movieapp.service.MovieService;
import org.example.movieapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdminController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/settings")
    public String showMovieList(Model model) {
        model.addAttribute("users", authenticationService.getAllUsers());
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "admin_settings";
    }

    @PostMapping("/delete/user")
    public String removeUser(@RequestBody String userId) {
        Long userIdLong = Long.parseLong(userId);
        authenticationService.removeUserById(userIdLong);
        return "redirect:/settings";
    }

    @PostMapping("/delete/movie")
    public String removeMovie(@RequestBody String movieId) {
        Long movieIdLong = Long.parseLong(movieId);
        movieService.deleteMovie(movieIdLong);
        return "redirect:/settings";
    }

    @PostMapping("/add/movie")
    public String addMovie(Movie movie) {
        movie.setId(null);
        movieRepository.save(movie);
        return "redirect:/movies";
    }
}
