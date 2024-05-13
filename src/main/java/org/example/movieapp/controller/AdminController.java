/**
 * Controller class for handling administrative operations related to movies and users.
 */
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

    /**
     * Display the admin settings page with lists of users and reviews.
     *
     * @param model Model to hold attributes for the view
     * @return The view name for admin settings page
     */
    @GetMapping("/settings")
    public String showMovieList(Model model) {
        model.addAttribute("users", authenticationService.getAllUsers());
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "admin_settings";
    }

    /**
     * Remove a user by ID.
     *
     * @param userId ID of the user to be removed
     * @return Redirect to the admin settings page
     */
    @PostMapping("/delete/user")
    public String removeUser(@RequestBody String userId) {
        Long userIdLong = Long.parseLong(userId);
        new Thread(() -> {
            authenticationService.removeUserById(userIdLong);
        }).start();
        return "redirect:/settings";
    }

    /**
     * Remove a movie by ID.
     *
     * @param movieId ID of the movie to be removed
     * @return Redirect to the admin settings page
     */
    @PostMapping("/delete/movie")
    public String removeMovie(@RequestBody String movieId) {
        Long movieIdLong = Long.parseLong(movieId);
        movieProxy.deleteMovie(movieIdLong);
        return "redirect:/settings";
    }

    /**
     * Add a new movie.
     *
     * @param movie New movie object to be added
     * @return Redirect to the movies page after adding the movie
     */
    @PostMapping("/add/movie")
    public String addMovie(Movie movie) {
        Movie newMovie = movieFactory.createMovie();
        newMovie.setTitle(movie.getTitle());
        newMovie.setDescription(movie.getDescription());
        newMovie.setYear(movie.getYear());
        movieProxy.addMovie(newMovie);
//        List<Admin> admins = authenticationService.getAllAdmins(); // TODO: implement getAllAdmins
//        for (Admin admin : admins) { // TODO: uncomment
//            movie.addObserver(admin); // TODO: uncomment
//        } // TODO: uncomment
        return "redirect:/movies";
    }

    /**
     * Upload an image for a movie.
     *
     * @param movieId ID of the movie for which image is uploaded
     * @param image   Image data in string format
     * @return ResponseEntity with success or failure message
     */
    @PostMapping("/add/image/{id}")
    public ResponseEntity<String> uploadMovieImage(@PathVariable("id") Long movieId,
                                                   @RequestBody String image) {
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body("Image is empty");
        }
        new Thread(() -> {
            try {
                Movie movie = movieService.getMovieById(movieId);
                if (movie == null) {
                    throw new IOException();
                }
                movieProxy.updateMovieImage(movieId, image);
            } catch (IOException error) {
                error.printStackTrace();
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
            }
        }).start();
        return ResponseEntity.ok("Image uploaded successfully");
    }
}
