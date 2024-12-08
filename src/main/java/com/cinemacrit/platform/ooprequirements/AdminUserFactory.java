package com.cinemacrit.platform.ooprequirements;

import com.cinemacrit.platform.models.AdminUser;

/**
 * Factory class for creating instances of AdminUser objects.
 */
public class AdminUserFactory implements UserFactory<AdminUser>{
    /**
     * Default constructor for AdminUserFactory.
     */
    public AdminUserFactory() {}

    /**
     * Creates a new instance of AdminUser.
     *
     * @return A new instance of AdminUser.
     */
    @Override
    public AdminUser createUser() {
        return new AdminUser();
    }
}