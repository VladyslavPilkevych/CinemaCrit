package org.example.movieapp.service.proxy;

import org.example.movieapp.model.Movie;

public interface MovieProxy {
    void addMovie(Movie movie);
    void deleteMovie(Long movieId);
    void updateMovieImage(Long movieId, String image);
}
