package org.example.movieapp.factory;

import org.example.movieapp.model.Review;

public class ReviewFactory extends BaseEntityFactory {
    @Override
    public Review createReview() {
        return new Review();
    }
}
