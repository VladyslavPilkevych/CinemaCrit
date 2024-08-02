/**
 * Model class representing an Admin user.
 */
package org.example.movieapp.model;

import jakarta.persistence.Entity;
import org.example.movieapp.util.MovieObserver;

@Entity
public class Admin extends Person implements MovieObserver {
    /**
     * Update method to receive notifications about movie updates.
     *
     * @param movie the movie that was updated
     */
    @Override
    public void update(Movie movie) {
        System.out.println("Admin received update about movie: " + movie.getTitle());
    }

}
