package org.example.movieapp.controller;

import org.example.movieapp.repository.ReviewRepository;
import org.example.movieapp.service.AuthenticationService;
import org.example.movieapp.service.MovieService;
import org.example.movieapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/settings")
    public String showMovieList(Model model) {
        model.addAttribute("movies", authenticationService.getAllUsers());
        return "admin_settings";
    }

}
