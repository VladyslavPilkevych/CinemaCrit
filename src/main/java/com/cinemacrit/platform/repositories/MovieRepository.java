package com.cinemacrit.platform.repositories;

import com.cinemacrit.platform.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on the Movie entity.
 * This interface extends the {@link JpaRepository}, providing built-in methods
 * for interacting with the Movie table in the database.
 *
 * The methods in this interface allow searching, sorting, and querying movies
 * based on various criteria, including their popularity and content.
 *
 * @see JpaRepository
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    /**
     * Retrieves all movies from the database, sorted by the release year in descending order.
     *
     * @return a list of movies ordered by year, from the most recent to the oldest.
     */
    List<Movie> findAllByOrderByYearDesc();

    /**
     * Retrieves a list of movies ordered by their popularity, based on the number of reviews.
     * Movies with the most reviews will appear first in the list.
     *
     * @return a list of movies ordered by popularity (i.e., by the number of reviews).
     */
    @Query("SELECT m FROM Movie m " +
            "LEFT JOIN Review r ON m.id = r.movieId " +
            "GROUP BY m.id " +
            "ORDER BY COUNT(r.id) DESC")
    List<Movie> findMoviesByPopularity();

    /**
     * Retrieves a list of movies whose title contains the given text, ignoring case.
     * This method can be used to search for movies based on a partial or full title.
     *
     * @param title the title or partial title of the movie to search for.
     * @return a list of movies that match the given title.
     */
    List<Movie> findByTitleContaining(String title);

    /**
     * Retrieves a list of movies whose description contains the given text, ignoring case.
     * This method can be used to search for movies based on a partial or full description.
     *
     * @param text the description text to search for.
     * @return a list of movies that match the given description.
     */
    List<Movie> findByDescriptionContaining(String text);
}
