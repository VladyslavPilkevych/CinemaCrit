/**
 * Factory class for creating User and Admin instances.
 */
package org.example.movieapp.factory;

import org.example.movieapp.model.Admin;
import org.example.movieapp.model.User;

public class PersonFactory extends BaseEntityFactory {
    /**
     * Creates a new User instance.
     *
     * @return a new User instance
     */
    @Override
    public User createUser() {
        return new User();
    }

    /**
     * Creates a new Admin instance.
     *
     * @return a new Admin instance
     */
    @Override
    public Admin createAdmin() {
        return new Admin();
    }
}
