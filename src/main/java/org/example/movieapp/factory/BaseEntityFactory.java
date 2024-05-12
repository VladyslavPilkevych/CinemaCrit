package org.example.movieapp.factory;

import org.example.movieapp.model.*;

public abstract class BaseEntityFactory implements EntityFactory {
    @Override
    public User createUser() {
        throw new UnsupportedOperationException("Method createUser() is not implemented");
    }

    @Override
    public Review createReview() {
        throw new UnsupportedOperationException("Method createReview() is not implemented");
    }

    @Override
    public Movie createMovie() {
        throw new UnsupportedOperationException("Method createMovie() is not implemented");
    }

    @Override
    public Admin createAdmin() {
        throw new UnsupportedOperationException("Method createAdmin() is not implemented");
    }
}
