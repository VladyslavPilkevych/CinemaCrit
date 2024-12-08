package com.cinemacrit.platform.controllers;

import com.cinemacrit.platform.models.Review;
import com.cinemacrit.platform.repositories.ReviewRepository;
import com.cinemacrit.platform.services.UserDetailsPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReviewControllerTest {

    private ReviewController reviewController;
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        reviewRepository = mock(ReviewRepository.class);
        reviewController = new ReviewController();
        reviewController.reviewRepository = reviewRepository;

        UserDetailsPrincipal mockPrincipal = mock(UserDetailsPrincipal.class);
        when(mockPrincipal.getUsername()).thenReturn("user1");

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockPrincipal);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testAddReview() {
        Review review = new Review();
        String result = reviewController.addReview("1", review);

        assertEquals("redirect:/movies/?id=1", result);
        verify(reviewRepository).save(review);
    }

    @Test
    void testRemoveMovie() {
        String result = reviewController.removeMovie(1L, 2L);

        assertEquals("redirect:/movies/?id=2", result);
        verify(reviewRepository, times(1)).deleteById(1L);
    }
}
