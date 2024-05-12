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

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getMoviesFilteredByDate() {
        return movieRepository.findAllByOrderByYearDesc();
    }

    public List<Movie> getMoviesFilteredByRate() {
        return movieRepository.findAllByOrderByAverageRatingDesc();
    }

    public List<Movie> getMoviesFilteredByPopularity() {
        return movieRepository.findMoviesByPopularity();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

//    public void saveMovieWithImage(Movie movie, byte[] imageData) {
//        movie.setImageData(imageData);
//        movieRepository.save(movie);
//    }
}

