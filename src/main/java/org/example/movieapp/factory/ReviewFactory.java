/**
 * Factory class for creating Review instances.
 */
package org.example.movieapp.factory;

import org.example.movieapp.model.Review;

public class ReviewFactory extends BaseEntityFactory {
    /**
     * Creates a new Review instance.
     *
     * @return a new Review instance
     */
    @Override
    public Review createReview() {
        return new Review();
    }
}
