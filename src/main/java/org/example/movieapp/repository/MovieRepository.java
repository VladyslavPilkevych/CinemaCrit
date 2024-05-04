package org.example.movieapp.repository;

import org.example.movieapp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByOrderByYearDesc();

    @Query("SELECT m " +
            "FROM Movie m LEFT JOIN m.reviews r " +
            "GROUP BY m.id " +
            "ORDER BY AVG(r.rating) DESC")
    List<Movie> findAllByOrderByAverageRatingDesc();

    @Query("SELECT m FROM Movie m " +
            "LEFT JOIN Review r ON m.id = r.movieId " +
            "GROUP BY m.id " +
            "ORDER BY COUNT(r.id) DESC")
    List<Movie> findMoviesByPopularity();
}
