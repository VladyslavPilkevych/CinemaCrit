/**
 * Interface for objects that observe changes in Movie objects.
 */
package org.example.movieapp.util;

import org.example.movieapp.model.Movie;

public interface MovieObserver {
    /**
     * Update method called when a movie object changes.
     *
     * @param movie the movie object that has been updated
     */
    void update(Movie movie);
}
