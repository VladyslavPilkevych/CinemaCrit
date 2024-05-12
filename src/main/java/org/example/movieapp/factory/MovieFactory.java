package org.example.movieapp.factory;

import org.example.movieapp.model.Movie;


public class MovieFactory extends BaseEntityFactory {
    @Override
    public Movie createMovie() {
        return new Movie();
    }
}
