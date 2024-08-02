/**
 * Service class for movie-related operations.
 */
package org.example.movieapp.service.impl;

import org.example.movieapp.model.Movie;
import org.example.movieapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    /**
     * Retrieves all movies.
     *
     * @return a list of all movies
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Retrieves movies filtered by date.
     *
     * @return a list of movies filtered by date
     */
    public List<Movie> getMoviesFilteredByDate() {
        return movieRepository.findAllByOrderByYearDesc();
    }

    /**
     * Retrieves movies filtered by average rating.
     *
     * @return a list of movies filtered by average rating
     */
    public List<Movie> getMoviesFilteredByRate() {
        return movieRepository.findAllByOrderByAverageRatingDesc();
    }

    /**
     * Retrieves movies filtered by popularity.
     *
     * @return a list of movies filtered by popularity
     */
    public List<Movie> getMoviesFilteredByPopularity() {
        return movieRepository.findMoviesByPopularity();
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param id the ID of the movie to retrieve
     * @return the movie with the given ID, or null if not found
     */
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    /**
     * Adds a new movie.
     *
     * @param movie the movie to add
     */
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    /**
     * Updates an existing movie.
     *
     * @param movie the movie to update
     */
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param id the ID of the movie to delete
     */
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

}

