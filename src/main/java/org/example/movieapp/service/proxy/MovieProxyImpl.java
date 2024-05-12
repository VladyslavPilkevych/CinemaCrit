package org.example.movieapp.service.proxy;

import org.example.movieapp.model.Movie;
import org.example.movieapp.service.impl.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieProxyImpl implements MovieProxy {

    @Autowired
    private MovieService movieService;

    @Override
    public void addMovie(Movie movie) {
        System.out.println("Movie title - '" + movie.getTitle() + "' was deleted");
        movieService.addMovie(movie);
    }
    @Override
    public void deleteMovie(Long movieId) {
        System.out.println("Movie #" + movieId + " was deleted");
        movieService.deleteMovie(movieId);
    }

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

