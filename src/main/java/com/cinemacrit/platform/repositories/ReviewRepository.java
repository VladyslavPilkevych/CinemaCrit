package com.cinemacrit.platform.repositories;

import com.cinemacrit.platform.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on the Review entity.
 * This interface extends {@link JpaRepository}, providing built-in methods for
 * interacting with the Review table in the database.
 *
 * The methods in this interface allow for querying reviews based on movie ID or username,
 * as well as updating reviews when a user's username changes.
 *
 * @see JpaRepository
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * Retrieves all reviews for a specific movie, identified by its movie ID.
     *
     * @param movieId the ID of the movie whose reviews are to be retrieved.
     * @return a list of reviews for the specified movie.
     */
    List<Review> findAllByMovieId(Long movieId);

    /**
     * Retrieves a review for a specific username.
     * This method searches for a review based on the username of the reviewer.
     *
     * @param username the username of the reviewer whose review is to be retrieved.
     * @return an Optional containing the review if found, or an empty Optional if no review is found.
     */
    @Query("SELECT r FROM Review r WHERE r.username = :username")
    Optional<Review> findReviewsByUsername(@Param("username") String username);

    /**
     * Updates the username in reviews where the old username is found.
     * This method is used when a user's username is changed, and it ensures that
     * all their associated reviews are updated with the new username.
     *
     * @param oldUsername the username to be replaced.
     * @param newUsername the new username to set in the reviews.
     */
    @Transactional
    @Modifying
    @Query("UPDATE Review c SET c.username = :newUsername WHERE c.username = :oldUsername")
    void updateUsernameForReview(String oldUsername, String newUsername);
}
