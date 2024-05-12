package org.example.movieapp.util;

import org.example.movieapp.model.Movie;

public interface MovieObserver {
    void update(Movie movie);
}
