/**
 * Factory class for creating Movie instances.
 */
package org.example.movieapp.factory;

import org.example.movieapp.model.Movie;


public class MovieFactory extends BaseEntityFactory {
    /**
     * Creates a new Movie instance.
     *
     * @return a new Movie instance
     */
    @Override
    public Movie createMovie() {
        return new Movie();
    }
}
