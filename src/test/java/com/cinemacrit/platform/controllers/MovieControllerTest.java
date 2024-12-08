package com.cinemacrit.platform.controllers;

import com.cinemacrit.platform.models.Movie;
import com.cinemacrit.platform.repositories.MovieRepository;
import com.cinemacrit.platform.repositories.ReviewRepository;
import com.cinemacrit.platform.roles.Roles;
import com.cinemacrit.platform.services.UserDetailsPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    private MovieController movieController;
    private MovieRepository movieRepository;
    private ReviewRepository reviewRepository;
    private Model model;

    @BeforeEach
    void setUp() {
        movieRepository = mock(MovieRepository.class);
        reviewRepository = mock(ReviewRepository.class);
        movieController = new MovieController();
        movieController.movieRepository = movieRepository;
        movieController.reviewRepository = reviewRepository;
        model = mock(Model.class);

        UserDetailsPrincipal mockPrincipal = mock(UserDetailsPrincipal.class);
        when(mockPrincipal.getRole()).thenReturn(Roles.ADMIN);
        when(mockPrincipal.getUsername()).thenReturn("admin");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockPrincipal);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testAddGet() {
        String result = movieController.add(model);
        verify(model).addAttribute(eq("movie"), any(Movie.class));
        assertEquals("home#Create", result);
    }

    @Test
    void testAddPost() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile("imageFile", "image.jpg", "image/jpeg", "test-image".getBytes());
        Movie movie = new Movie();

        String result = movieController.add(movie, imageFile);

        verify(movieRepository, times(1)).save(movie);
        assertEquals("redirect:/home#Home", result);
    }

    @Test
    void testShowMovieDetails_movieFound() {
        Movie movie = new Movie();
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(reviewRepository.findAllByMovieId(1L)).thenReturn(null);

        String result = movieController.showMovieDetails(1L, model);

        verify(model).addAttribute("movie", movie);
        assertEquals("movie-page", result);
    }

    @Test
    void testShowMovieDetails_movieNotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        String result = movieController.showMovieDetails(1L, model);

        assertEquals("redirect:/home", result);
    }
}
