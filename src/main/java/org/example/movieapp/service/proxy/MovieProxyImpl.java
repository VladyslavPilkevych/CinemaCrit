/**
 * Implementation of the MovieProxy interface.
 */
package org.example.movieapp.service.proxy;

import org.example.movieapp.model.Movie;
import org.example.movieapp.service.impl.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieProxyImpl implements MovieProxy {

    @Autowired
    private MovieService movieService;

    /**
     * Adds a new movie.
     *
     * @param movie the movie to add
     */
    @Override
    public void addMovie(Movie movie) {
        System.out.println("Movie title - '" + movie.getTitle() + "' was deleted");
        movieService.addMovie(movie);
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param movieId the ID of the movie to delete
     */
    @Override
    public void deleteMovie(Long movieId) {
        System.out.println("Movie #" + movieId + " was deleted");
        movieService.deleteMovie(movieId);
    }

    /**
     * Updates the image of a movie.
     *
     * @param movieId the ID of the movie
     * @param image   the new image URL
     */
    @Override
    public void updateMovieImage(Long movieId, String image) {
        System.out.println("Movie #" + movieId + " image was updated");
        Movie movie = movieService.getMovieById(movieId);
        if (movie != null) {
            movie.setImage(image);
            movieService.updateMovie(movie);
        }
    }
}

