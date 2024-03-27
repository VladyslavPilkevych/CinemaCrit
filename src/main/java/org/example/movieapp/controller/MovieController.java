package org.example.movieapp.controller;

import org.example.movieapp.model.Movie;
import org.example.movieapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

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
        if (movie == null) {
            return "redirect:/movies";
        }
        model.addAttribute("movie", movie);
//        System.out.println(movieService.getMovieById(id));
        return "movie_details";
    }
}
