/**
 * Controller class for handling movie-related operations.
 */
package org.example.movieapp.controller;

import org.example.movieapp.factory.ReviewFactory;
import org.example.movieapp.model.Movie;
import org.example.movieapp.model.Review;
import org.example.movieapp.repository.ReviewRepository;
import org.example.movieapp.service.impl.MovieService;
import org.example.movieapp.service.impl.ReviewService;
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

    final private ReviewFactory reviewFactory = new ReviewFactory();

    /**
     * Display the list of movies.
     *
     * @param filter Filter parameter to sort movies
     * @param model  Model to hold attributes for the view
     * @return The view name for the movie list page
     */
    @GetMapping("/movies")
    public String showMovieList(@RequestParam(name = "filter", required = false) String filter, Model model) {
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
        return "movie_list";
    }

    /**
     * Display details of a specific movie.
     *
     * @param id    ID of the movie to display details
     * @param model Model to hold attributes for the view
     * @return The view name for the movie details page
     */
    @GetMapping("/movies/")
    public String showMovieDetails(@RequestParam(name="id", defaultValue = "") Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        List<Review> reviews = reviewService.getReviewsByMovieId(id).reversed();
        if (movie == null) {
            return "redirect:/movies";
        }
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
        model.addAttribute("review", reviewFactory.createReview());
        model.addAttribute("dateUtils", new DateUtils());
        return "movie_details";
    }

    /**
     * Add a review for a movie.
     *
     * @param movieId ID of the movie to add review
     * @param review  Review object containing review details
     * @return Redirect to the movie details page after adding review
     */
    @PostMapping("/movies/{id}")
    public String addReview(@PathVariable(name = "id") String movieId, @RequestBody String body, Review review) {
        Long movieIdLong = Long.parseLong(movieId);
        review.setId(null);
        review.setCreatedDate(LocalDateTime.now());
        review.setMovieId(movieIdLong);

        reviewRepository.save(review);
        return "redirect:/movies/?id="+movieId;
    }

}
