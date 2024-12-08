package com.cinemacrit.platform.services;

import com.cinemacrit.platform.models.Movie;
import com.cinemacrit.platform.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing movie-related operations.
 * Provides methods to perform CRUD operations on movies and retrieve movies based on various filters.
 */
@Service
public class MovieService {

    /**
     * Public constructor to prevent instantiation of the utility class.
     * The class contains only static methods and is not intended to be instantiated.
     */
    public MovieService() {}

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Retrieves all movies from the repository.
     *
     * @return a list of all movies.
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Retrieves all movies ordered by their release year in descending order.
     *
     * @return a list of movies filtered by release year, with the most recent first.
     */
    public List<Movie> getMoviesFilteredByDate() {
        return movieRepository.findAllByOrderByYearDesc();
    }

    /**
     * Retrieves movies ordered by their popularity, based on the number of reviews they have.
     *
     * @return a list of movies ordered by popularity.
     */
    public List<Movie> getMoviesFilteredByPopularity() {
        return movieRepository.findMoviesByPopularity();
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id the ID of the movie to retrieve.
     * @return the movie with the specified ID, or null if no such movie exists.
     */
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new movie to the repository.
     *
     * @param movie the movie to add.
     */
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    /**
     * Updates an existing movie in the repository.
     *
     * @param movie the movie with updated information.
     */
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    /**
     * Deletes a movie by its ID from the repository.
     *
     * @param id the ID of the movie to delete.
     */
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

}
