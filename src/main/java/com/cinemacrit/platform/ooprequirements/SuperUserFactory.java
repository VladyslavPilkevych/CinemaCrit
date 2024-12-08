package com.cinemacrit.platform.ooprequirements;

import com.cinemacrit.platform.models.SuperUser;

/**
 * Factory class for creating instances of SuperUser.
 */
public class SuperUserFactory implements UserFactory<SuperUser> {
    /**
     * Default constructor for SuperUserFactory.
     */
    public SuperUserFactory() {}
    /**
     * Creates and returns a new instance of SuperUser.
     *
     * @return A new instance of SuperUser.
     */
    @Override
    public SuperUser createUser() {
        return new SuperUser();
    }
}