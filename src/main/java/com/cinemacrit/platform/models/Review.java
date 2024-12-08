package com.cinemacrit.platform.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a review for a movie.
 * The Review entity contains information about a user's review for a movie, including the movie ID,
 * username, comment, rating, and the date the review was created.
 * This entity is used to store and retrieve review-related data in the database.
 */
@Entity
public class Review implements Serializable {

    /**
     * The unique identifier of the review.
     * This field is automatically generated when a new review is created.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The ID of the movie being reviewed.
     * This field links the review to a specific movie.
     */
    private Long movieId;

    /**
     * The username of the user who wrote the review.
     * This field stores the username of the user that submitted the review.
     */
    private String username;

    /**
     * The content of the review comment.
     * This field stores the text of the review written by the user.
     */
    private String comment;

    /**
     * The rating given by the user for the movie.
     * The rating is typically an integer representing the score given to the movie, for example, from 1 to 5.
     */
    private int rating;

    /**
     * The date and time when the review was created.
     * This field stores the timestamp when the review was submitted.
     */
    private LocalDateTime createdDate;

    /**
     * Default constructor for Review.
     * This constructor is provided by the JPA framework to instantiate a new Review object.
     * It does not require any custom initialization logic.
     */
    public Review() {
        // Default constructor for JPA
    }

    /**
     * Gets the creation date and time of the review.
     *
     * @return the date and time when the review was created.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the creation date and time of the review.
     *
     * @param createdDate the date and time when the review was created.
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the unique identifier of the review.
     *
     * @return the unique ID of the review.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the review.
     *
     * @param id the unique ID to set for the review.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the ID of the movie being reviewed.
     *
     * @return the movie ID associated with the review.
     */
    public Long getMovieId() {
        return movieId;
    }

    /**
     * Sets the ID of the movie being reviewed.
     *
     * @param movieId the movie ID to set for the review.
     */
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    /**
     * Gets the username of the user who wrote the review.
     *
     * @return the username of the user who submitted the review.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user who wrote the review.
     *
     * @param username the username to set for the review.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the content of the review comment.
     *
     * @return the text of the review comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the content of the review comment.
     *
     * @param comment the review comment text to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the rating given to the movie in the review.
     *
     * @return the rating score given by the user.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating for the movie in the review.
     *
     * @param rating the rating score to set for the review.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
}
