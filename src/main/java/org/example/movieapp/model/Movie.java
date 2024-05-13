/**
 * Model class representing a movie.
 */
package org.example.movieapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.example.movieapp.util.MovieObserver;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie extends Blank {

    @Column(columnDefinition = "BLOB")
    private String image;

    /**
     * Gets the image of the movie.
     *
     * @return the image of the movie
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image of the movie.
     *
     * @param image the image of the movie
     */
    public void setImage(String image) {
        this.image = image;
    }

    @OneToMany(mappedBy = "movieId", cascade = CascadeType.ALL)
    private List<Review> reviews;

    /**
     * Calculates the average rating of the movie based on its reviews.
     *
     * @return the average rating of the movie
     */
    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0.0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }

        return totalRating / reviews.size();
    }

    private static List<MovieObserver> observers = new ArrayList<>();

    /**
     * Adds an observer to be notified of changes to the movie.
     *
     * @param observer the observer to add
     */
    public void addObserver(MovieObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer the observer to remove
     */
    public void removeObserver(MovieObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers of changes to the movie.
     */
    public void notifyObservers() {
        for (MovieObserver observer : observers) {
            observer.update(this);
        }
    }
}
