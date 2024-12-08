package com.cinemacrit.platform.ooprequirements;

import com.cinemacrit.platform.models.User;

/**
 * Interface for user factories.
 *
 * @param <T> the type of user to create.
 */
public interface UserFactory <T extends User> {
    /**
     * Creates and returns a new instance of the specified user type.
     *
     * @return A new instance of the specified user type.
     */
    T createUser();
}