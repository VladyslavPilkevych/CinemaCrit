/**
 * Model class representing a user.
 */
package org.example.movieapp.model;

import jakarta.persistence.*;

@Entity
public class User extends Person {

    private String email;

    /**
     * Gets the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the email of the user.
     *
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
