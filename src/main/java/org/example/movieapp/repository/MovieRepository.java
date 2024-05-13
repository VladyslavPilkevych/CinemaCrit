/**
 * Repository interface for Movie entities.
 */
package org.example.movieapp.repository;

import org.example.movieapp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    /**
     * Finds all movies ordered by year in descending order.
     *
     * @return a list of movies ordered by year in descending order
     */
    List<Movie> findAllByOrderByYearDesc();

    /**
     * Finds all movies ordered by average rating in descending order.
     *
     * @return a list of movies ordered by average rating in descending order
     */
    @Query("SELECT m " +
            "FROM Movie m LEFT JOIN m.reviews r " +
            "GROUP BY m.id " +
            "ORDER BY AVG(r.rating) DESC")
    List<Movie> findAllByOrderByAverageRatingDesc();

    /**
     * Finds movies by popularity.
     *
     * @return a list of movies ordered by popularity
     */
    @Query("SELECT m FROM Movie m " +
            "LEFT JOIN Review r ON m.id = r.movieId " +
            "GROUP BY m.id " +
            "ORDER BY COUNT(r.id) DESC")
    List<Movie> findMoviesByPopularity();
}
