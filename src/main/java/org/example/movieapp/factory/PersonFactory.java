package org.example.movieapp.factory;

import org.example.movieapp.model.Admin;
import org.example.movieapp.model.User;

public class PersonFactory extends BaseEntityFactory {
    @Override
    public User createUser() {
        return new User();
    }

    @Override
    public Admin createAdmin() {
        return new Admin();
    }
}
