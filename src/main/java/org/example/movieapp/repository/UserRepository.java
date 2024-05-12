/**
 * Repository interface for User entities.
 */
package org.example.movieapp.repository;

import org.example.movieapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by username.
     *
     * @param username the username of the user to find
     * @return an optional containing the user if found, otherwise empty
     */
    Optional<User> findByUsername(String username);
    /**
     * Finds a user by email.
     *
     * @param email the email of the user to find
     * @return an optional containing the user if found, otherwise empty
     */
    Optional<User> findByEmail(String email);
}
