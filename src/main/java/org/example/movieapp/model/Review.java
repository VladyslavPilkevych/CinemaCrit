/**
 * Model class representing a review.
 */
package org.example.movieapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

@Entity
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long movieId;
    private String username;
    private String comment;
    private int rating;
    private LocalDateTime createdDate;

    /**
     * Gets the creation date of the review.
     *
     * @return the creation date of the review
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    /**
     * Sets the creation date of the review.
     *
     * @param createdDate the creation date of the review
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * Gets the ID of the review.
     *
     * @return the ID of the review
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the ID of the review.
     *
     * @param id the ID of the review
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Gets the ID of the movie being reviewed.
     *
     * @return the ID of the movie being reviewed
     */
    public Long getMovieId() {
        return movieId;
    }
    /**
     * Sets the ID of the movie being reviewed.
     *
     * @param movieId the ID of the movie being reviewed
     */
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
    /**
     * Gets the username of the reviewer.
     *
     * @return the username of the reviewer
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the reviewer.
     *
     * @param username the username of the reviewer
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Gets the comment of the review.
     *
     * @return the comment of the review
     */
    public String getComment() {
        return comment;
    }
    /**
     * Sets the comment of the review.
     *
     * @param comment the comment of the review
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /**
     * Gets the rating of the review.
     *
     * @return the rating of the review
     */
    public int getRating() {
        return rating;
    }
    /**
     * Sets the rating of the review.
     *
     * @param rating the rating of the review
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Serializes the review object to a file.
     *
     * @param review   the review object to serialize
     * @param fileName the name of the file to save the serialized review
     */
    public static void serializeReview(Review review, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(review);
            out.close();
            fileOut.close();
            System.out.println("Объект успешно сериализован и сохранен в " + fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}