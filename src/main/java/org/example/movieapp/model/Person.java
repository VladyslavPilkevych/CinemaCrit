/**
 * Base class representing a person.
 */
package org.example.movieapp.model;

import jakarta.persistence.*;

@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    /**
     * Gets the ID of the person.
     *
     * @return the ID of the person
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the ID of the person.
     *
     * @param id the ID of the person
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Gets the username of the person.
     *
     * @return the username of the person
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the person.
     *
     * @param username the username of the person
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Gets the password of the person.
     *
     * @return the password of the person
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the person.
     *
     * @param password the password of the person
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
