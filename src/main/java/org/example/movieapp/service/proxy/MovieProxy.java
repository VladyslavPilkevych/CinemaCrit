/**
 * Proxy interface for movie-related operations.
 */
package org.example.movieapp.service.proxy;

import org.example.movieapp.model.Movie;

public interface MovieProxy {
    /**
     * Adds a new movie.
     *
     * @param movie the movie to add
     */
    void addMovie(Movie movie);

    /**
     * Deletes a movie by its ID.
     *
     * @param movieId the ID of the movie to delete
     */
    void deleteMovie(Long movieId);

    /**
     * Updates the image of a movie.
     *
     * @param movieId the ID of the movie
     * @param image   the new image URL
     */
    void updateMovieImage(Long movieId, String image);
}
