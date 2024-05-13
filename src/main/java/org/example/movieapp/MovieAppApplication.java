package org.example.movieapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the MovieApp application.
 */
@SpringBootApplication
public class MovieAppApplication {
    /**
     * Main method to start the Spring Boot application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MovieAppApplication.class, args);
    }
}
