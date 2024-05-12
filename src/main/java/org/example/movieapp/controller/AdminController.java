package org.example.movieapp.controller;

import org.example.movieapp.factory.MovieFactory;
import org.example.movieapp.model.Movie;
import org.example.movieapp.repository.MovieRepository;
import org.example.movieapp.service.impl.AuthenticationService;
import org.example.movieapp.service.impl.MovieService;
import org.example.movieapp.service.impl.ReviewService;
import org.example.movieapp.service.proxy.MovieProxy;
import org.example.movieapp.util.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private MovieProxy movieProxy;

    final private MovieFactory movieFactory = new MovieFactory();

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
        movieProxy.deleteMovie(movieIdLong);
        return "redirect:/settings";
    }

    @PostMapping("/add/movie")
    public String addMovie(Movie movie) {
        Movie newMovie = movieFactory.createMovie();
        newMovie.setTitle(movie.getTitle());
        newMovie.setDescription(movie.getDescription());
        newMovie.setYear(movie.getYear());
        movieProxy.addMovie(newMovie);
        return "redirect:/movies";
    }

    @PostMapping("/add/image/{id}")
    public ResponseEntity<String> uploadMovieImage(@PathVariable("id") Long movieId,
        @RequestBody String image) {
//        System.out.println("Request Body: " + image);

//                                                   @RequestBody MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body("Image is empty");
        }
        try {
            Movie movie = movieService.getMovieById(movieId);
            if (movie == null) {
                throw new IOException();
//                return ResponseEntity.notFound().build();
            }

//            byte[] imageData = image.getBytes();
//            movie.setImage(image);
//            movieService.updateMovie(movie);
            movieProxy.updateMovieImage(movieId, image);

            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException error) {
            error.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }
}
