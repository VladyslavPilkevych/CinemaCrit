package com.cinemacrit.platform.controllers;

import com.cinemacrit.platform.models.Movie;
import com.cinemacrit.platform.repositories.MovieRepository;
import com.cinemacrit.platform.repositories.ReviewRepository;
import com.cinemacrit.platform.repositories.SimpleUserRepository;
import com.cinemacrit.platform.roles.Roles;
import com.cinemacrit.platform.services.UserDetailsPrincipal;
import com.cinemacrit.platform.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

/**
 * Controller class for managing movies.
 */
@Controller
@RequestMapping("/movie")
public class MovieController {
    /**
     * Default constructor for the MovieController.
     */
    public MovieController() {}

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    private SimpleUserRepository simpleUserRepository;

    /**
     * Displays the movie creation page.
     * Initializes an empty movie object and adds it to the model for use in the view.
     *
     * @param model the model to be used by the view
     * @return the name of the view to be rendered (home#Create)
     */
    @GetMapping("/add")
    public String add(Model model) {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "home#Create";
    }

    /**
     * Handles the form submission for adding a new movie.
     * Takes a movie object and an image file, processes the image, and saves the movie to the repository.
     *
     * @param movie the movie details provided by the user
     * @param imageFile the image file uploaded by the user for the movie
     * @return a redirect URL to the home page
     */
    @PostMapping("/add")
    public String add(@ModelAttribute Movie movie, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                String base64Image = "data:" + imageFile.getContentType() + ";base64," +
                        Base64.getEncoder().encodeToString(imageFile.getBytes());
                movie.setImage(base64Image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        movieRepository.save(movie);
        return "redirect:/home#Home";
    }

    /**
     * Displays the details of a specific movie along with its reviews.
     * Retrieves the movie information from the repository and adds it to the model.
     * Also adds the current user's username, role, and formatted date utilities to the model.
     *
     * @param id the ID of the movie to display
     * @param model the model to be used by the view
     * @return the name of the view to be rendered (movie-page), or a redirect to the home page if the movie is not found
     */
    @GetMapping("/")
    public String showMovieDetails(@RequestParam(name="id", defaultValue = "") Long id, Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return "redirect:/home";
        }
        UserDetailsPrincipal userDetailsPrincipal = (UserDetailsPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviewRepository.findAllByMovieId(id));
        model.addAttribute("currentUsername", userDetailsPrincipal.getUsername());
        model.addAttribute("isAdmin", userDetailsPrincipal.getRole() == Roles.ADMIN || userDetailsPrincipal.getRole() == Roles.SUPER);
        model.addAttribute("dateUtils", new DateUtils());
        return "movie-page";
    }

    /**
     * Handles the submission of an edit form for a movie.
     *
     * This method updates an existing movie in the database with the data submitted through the form.
     * The movie to update is identified by its ID provided in the URL path, and the updated data
     * is passed as part of the request body.
     *
     * @param movieId the ID of the movie to be edited, extracted from the URL path.
     * @param movie the updated movie data from the form, mapped to the {@link Movie} object.
     * @return a redirect URL to the updated movie's detail page.
     *
     * @throws RuntimeException if a movie with the specified ID is not found in the database.
     */
    @PostMapping("/edit/{id}")
    public String addReview(@PathVariable(name = "id") String movieId, @ModelAttribute Movie movie) {
        Long movieIdLong = Long.parseLong(movieId);

        Movie currentMovie = movieRepository.findById(movieIdLong).orElseThrow(() -> new RuntimeException("User not found"));

        currentMovie.setTitle(movie.getTitle());
        currentMovie.setDescription(movie.getDescription());
        currentMovie.setYear(movie.getYear());

        movieRepository.save(currentMovie);

        return "redirect:/movies/?id="+movieId;
    }
}