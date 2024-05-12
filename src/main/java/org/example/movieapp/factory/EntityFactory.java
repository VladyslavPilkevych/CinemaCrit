package org.example.movieapp.factory;

import org.example.movieapp.model.Admin;
import org.example.movieapp.model.Movie;
import org.example.movieapp.model.Review;
import org.example.movieapp.model.User;

public interface EntityFactory {
    User createUser();
    Review createReview();
    Movie createMovie();
    Admin createAdmin();
}