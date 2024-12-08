package com.cinemacrit.platform.models;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents a movie in the system.
 * The Movie entity contains information about a movie, including its title, description, year, and an optional image.
 * This entity is used to store and retrieve movie-related data in the database.
 *
 * Implements the Comparable interface to allow comparison of Movie objects based on their titles.
 */
@Entity
public class Movie implements Comparable<Movie> {
    /**
     * Public constructor for creating a Movie object.
     * This constructor is intended for external use when creating an instance of DateUtils.
     */
    public Movie() {}

    /**
     * The unique identifier of the movie.
     * This field is automatically generated when a new movie is created.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the movie.
     * The title is a required field and provides the name of the movie.
     */
    private String title;

    /**
     * A description of the movie.
     * Provides a short summary or overview of the movie's content.
     */
    private String description;

    /**
     * The year the movie was released.
     * This field is used to store the year of the movie's premiere or release.
     */
    private int year;

    /**
     * The image associated with the movie.
     * This field is stored as a Base64 encoded string representing the movie's image or poster.
     */
    @Column(columnDefinition = "BLOB", nullable = true)
    private String image;

    /**
     * Gets the unique identifier of the movie.
     *
     * @return the unique ID of the movie.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the movie.
     *
     * @param id the unique ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of the movie.
     *
     * @return the title of the movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the movie.
     *
     * @param title the title of the movie to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the movie.
     *
     * @return the description of the movie.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the movie.
     *
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the release year of the movie.
     *
     * @return the release year of the movie.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the release year of the movie.
     *
     * @param year the year of release to set.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the image associated with the movie.
     *
     * @return the image in Base64 format.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image associated with the movie.
     *
     * @param image the Base64 encoded image to set.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Compares this movie to another movie based on the movie title.
     * Implements the {@link Comparable} interface to allow sorting of movies alphabetically by title.
     *
     * @param other the movie to compare with.
     * @return a negative integer, zero, or a positive integer as this movie's title is less than, equal to, or greater than the specified movie's title.
     */
    @Override
    public int compareTo(Movie other) {
        return this.title.compareTo(other.title);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two Movie objects are considered equal if they have the same ID.
     *
     * @param o the object to compare with.
     * @return true if this movie has the same ID as the specified object, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the movie's ID.
     *
     * @return a hash code value for this movie.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
