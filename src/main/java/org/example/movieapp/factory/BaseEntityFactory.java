/**
 * Abstract factory class for creating entities.
 */
package org.example.movieapp.factory;

import org.example.movieapp.model.*;

public abstract class BaseEntityFactory implements EntityFactory {
    /**
     * Creates a new user instance.
     *
     * @return a new User instance
     * @throws UnsupportedOperationException if the method is not implemented
     */
    @Override
    public User createUser() {
        throw new UnsupportedOperationException("Method createUser() is not implemented");
    }

    /**
     * Creates a new review instance.
     *
     * @return a new Review instance
     * @throws UnsupportedOperationException if the method is not implemented
     */
    @Override
    public Review createReview() {
        throw new UnsupportedOperationException("Method createReview() is not implemented");
    }

    /**
     * Creates a new movie instance.
     *
     * @return a new Movie instance
     * @throws UnsupportedOperationException if the method is not implemented
     */
    @Override
    public Movie createMovie() {
        throw new UnsupportedOperationException("Method createMovie() is not implemented");
    }

    /**
     * Creates a new admin instance.
     *
     * @return a new Admin instance
     * @throws UnsupportedOperationException if the method is not implemented
     */
    @Override
    public Admin createAdmin() {
        throw new UnsupportedOperationException("Method createAdmin() is not implemented");
    }
}
