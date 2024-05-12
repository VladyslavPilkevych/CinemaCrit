/**
 * Repository interface for Review entities.
 */
package org.example.movieapp.repository;

import org.example.movieapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    /**
     * Finds all reviews by movie ID.
     *
     * @param movieId the ID of the movie
     * @return a list of reviews for the specified movie ID
     */
    List<Review> findAllByMovieId(Long movieId);
}
