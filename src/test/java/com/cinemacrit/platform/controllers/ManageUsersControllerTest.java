package com.cinemacrit.platform.controllers;

import com.cinemacrit.platform.models.SimpleUser;
import com.cinemacrit.platform.models.User;
import com.cinemacrit.platform.repositories.AdminUserRepository;
import com.cinemacrit.platform.repositories.MovieRepository;
import com.cinemacrit.platform.repositories.SimpleUserRepository;
import com.cinemacrit.platform.repositories.SuperUserRepository;
import com.cinemacrit.platform.roles.Roles;
import com.cinemacrit.platform.services.CustomUserDetailsService;
import com.cinemacrit.platform.services.UserDetailsPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ManageUsersControllerTest {

    private ManageUsersController manageUsersController;
    private CustomUserDetailsService customUserDetailsService;
    private SimpleUserRepository simpleUserRepository;
    private AdminUserRepository adminUserRepository;
    private SuperUserRepository superUserRepository;
    private MovieRepository movieRepository;
    private Model model;

    @BeforeEach
    void setUp() {
        customUserDetailsService = mock(CustomUserDetailsService.class);
        simpleUserRepository = mock(SimpleUserRepository.class);
        adminUserRepository = mock(AdminUserRepository.class);
        superUserRepository = mock(SuperUserRepository.class);
        movieRepository = mock(MovieRepository.class);
        manageUsersController = new ManageUsersController();
        manageUsersController.customUserDetailsService = customUserDetailsService;
        manageUsersController.simpleUserRepository = simpleUserRepository;
        manageUsersController.adminUserRepository = adminUserRepository;
        manageUsersController.superUserRepository = superUserRepository;
        manageUsersController.movieRepository = movieRepository;
        model = mock(Model.class);

        // Mock Security Context
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
    void testAdminHome_AdminRole() {
        when(simpleUserRepository.count()).thenReturn(10L);
        when(adminUserRepository.count()).thenReturn(5L);
        when(superUserRepository.count()).thenReturn(2L);
        when(movieRepository.count()).thenReturn(100L);
        when(simpleUserRepository.findAll()).thenReturn(new ArrayList<>());

        String result = manageUsersController.adminHome(model);

        verify(model).addAttribute("simpleUsersCount", 10L);
        verify(model).addAttribute("adminUsersCount", 5L);
        verify(model).addAttribute("superUsersCount", 2L);
        verify(model).addAttribute("movieCount", 100L);
        verify(model).addAttribute(eq("users"), any(List.class));
        assertEquals("admin-home", result);
    }

    @Test
    void testAddUserForm() {
        String result = manageUsersController.addUser(model);
        verify(model).addAttribute(any(User.class));
        assertEquals("super-add", result);
    }

    @Test
    void testAddUser_Success() {
        User user = new User();
        when(customUserDetailsService.addUser(user)).thenReturn(true);

        String result = manageUsersController.addUser(user);
        assertEquals("redirect:/admin/add?success", result);
    }

    @Test
    void testAddUser_Failure() {
        User user = new User();
        when(customUserDetailsService.addUser(user)).thenReturn(false);

        String result = manageUsersController.addUser(user);
        assertEquals("redirect:/admin/add?failure", result);
    }

    @Test
    void testBlockUser_AdminRole() {
        SimpleUser simpleUser = new SimpleUser();
        when(simpleUserRepository.findByUsernameContaining("test"))
                .thenReturn(List.of(simpleUser)); // Return SimpleUser type

        String result = manageUsersController.blockUser("test");

        assertEquals("redirect:/admin", result);
        assertEquals(1, ManageUsersController.usersToBlock.size());
        assertEquals(simpleUser, ManageUsersController.usersToBlock.get(0));
    }

    @Test
    void testBlockUsersByEmail_Found() {
        SimpleUser simpleUser = new SimpleUser();
        when(simpleUserRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(simpleUser));

        String result = manageUsersController.blockUsers("test@example.com");
        verify(simpleUserRepository).save(simpleUser);
        assertEquals(false, simpleUser.getEnabled());
        assertEquals("redirect:/admin", result);
    }

    @Test
    void testBlockUsersByEmail_NotFound() {
        when(simpleUserRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.empty());
        when(adminUserRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.empty());
        when(superUserRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.empty());

        String result = manageUsersController.blockUsers("test@example.com");
        verify(simpleUserRepository, never()).save(any());
        verify(adminUserRepository, never()).save(any());
        verify(superUserRepository, never()).save(any());
        assertEquals("redirect:/admin", result);
    }

    @Test
    void testUnblockUsersByEmail_Found() {
        SimpleUser simpleUser = new SimpleUser();
        when(simpleUserRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(simpleUser));

        String result = manageUsersController.unblockUsers("test@example.com");
        verify(simpleUserRepository).save(simpleUser);
        assertEquals(true, simpleUser.getEnabled());
        assertEquals("redirect:/admin", result);
    }
}
