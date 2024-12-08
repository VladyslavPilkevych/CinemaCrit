package com.cinemacrit.platform.controllers;

import com.cinemacrit.platform.models.SimpleUser;
import com.cinemacrit.platform.repositories.MovieRepository;
import com.cinemacrit.platform.repositories.ReviewRepository;
import com.cinemacrit.platform.repositories.SimpleUserRepository;
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

class UserControllerTest {

    private UserController userController;
    private SimpleUserRepository simpleUserRepository;
    private ReviewRepository reviewRepository;
    private MovieRepository movieRepository;
    private Model model;

    @BeforeEach
    void setUp() {
        simpleUserRepository = mock(SimpleUserRepository.class);
        reviewRepository = mock(ReviewRepository.class);
        movieRepository = mock(MovieRepository.class);
        userController = new UserController();
        userController.simpleUserRepository = simpleUserRepository;
        userController.reviewRepository = reviewRepository;
        userController.movieRepository = movieRepository;
        model = mock(Model.class);

        // Mock Security Context
        UserDetailsPrincipal mockPrincipal = mock(UserDetailsPrincipal.class);
        when(mockPrincipal.getId()).thenReturn(1L);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockPrincipal);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetProfile() {
        SimpleUser user = new SimpleUser();
        user.setId(1L);
        user.setUsername("user1");

        when(simpleUserRepository.findById(1L)).thenReturn(Optional.of(user));

        String result = userController.getProfile(model);

        verify(model).addAttribute(eq("user"), eq(user));
        assertEquals("user-profile", result);
    }

    @Test
    void testUploadAvatar() throws Exception {
        MockMultipartFile avatar = new MockMultipartFile("avatar", "avatar.jpg", "image/jpeg", "test-avatar".getBytes());
        SimpleUser user = new SimpleUser();

        when(simpleUserRepository.findById(1L)).thenReturn(Optional.of(user));

        String result = userController.uploadAvatar(avatar);

        verify(simpleUserRepository).save(user);
        assertEquals("redirect:/profile", result);
    }
}
