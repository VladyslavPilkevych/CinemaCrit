/**
 * Repository interface for Movie entities.
 */
package org.example.movieapp.repository;

import org.example.movieapp.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findAllByOrderByYearDesc();
}
