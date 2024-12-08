//package com.cinemacrit.platform.services;
//
//import com.cinemacrit.platform.models.AdminUser;
//import com.cinemacrit.platform.models.SimpleUser;
//import com.cinemacrit.platform.models.SuperUser;
//import com.cinemacrit.platform.repositories.AdminUserRepository;
//import com.cinemacrit.platform.repositories.SimpleUserRepository;
//import com.cinemacrit.platform.repositories.SuperUserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class CustomUserDetailsServiceTest {
//
//    @InjectMocks
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Mock
//    private SimpleUserRepository simpleUserRepository;
//
//    @Mock
//    private AdminUserRepository adminUserRepository;
//
//    @Mock
//    private SuperUserRepository superUserRepository;
//
//    @Mock
//    private Authentication authentication;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        SecurityContextHolder.setContext(mock(SecurityContext.class));
//    }
//
//    @Test
//    public void testLoadUserByUsername_UserFound() {
//        String email = "user@example.com";
//        SimpleUser user = new SimpleUser();
//        user.setEmail(email);
//        when(simpleUserRepository.findByEmail(email)).thenReturn(Optional.of(user));
//
//        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
//
//        verify(simpleUserRepository).findByEmail(email);
//        assert(userDetails.getUsername()).equals(email);
//    }
//
//    @Test
//    public void testLoadUserByUsername_UserNotFound() {
//        String email = "nonexistent@example.com";
//        when(simpleUserRepository.findByEmail(email)).thenReturn(Optional.empty());
//        when(adminUserRepository.findByEmail(email)).thenReturn(Optional.empty());
//        when(superUserRepository.findByEmail(email)).thenReturn(Optional.empty());
//
//        try {
//            customUserDetailsService.loadUserByUsername(email);
//        } catch (UsernameNotFoundException e) {
//            assert(e.getMessage()).contains("Couldn't find user with the email: " + email);
//        }
//    }
//
//    @Test
//    public void testRootSuperUserPersist_NoSuperUserExists() throws IOException {
//        when(superUserRepository.count()).thenReturn(0L);
//
//        customUserDetailsService.rootSuperUserPersist();
//
//        verify(superUserRepository).save(any(SuperUser.class));
//        File file = new File("credentials.txt");
//        assert(file.exists());
//    }
//
//    @Test
//    public void testRootSuperUserPersist_SuperUserExists() throws IOException, ClassNotFoundException {
//        SuperUser superUser = new SuperUser();
//        superUser.setEmail("super@user.com");
//        superUser.setRoot(true);
//        when(superUserRepository.count()).thenReturn(1L);
//        when(superUserRepository.findById(1L)).thenReturn(Optional.of(superUser));
//
//        customUserDetailsService.rootSuperUserPersist();
//
//        verify(superUserRepository).save(any(SuperUser.class));
//    }
//
//    @Test
//    public void testAddUser_AddSimpleUser() {
//        SimpleUser simpleUser = new SimpleUser();
//        simpleUser.setEmail("simple@user.com");
//        when(simpleUserRepository.findByEmail(simpleUser.getEmail())).thenReturn(Optional.empty());
//
//        boolean result = customUserDetailsService.addUser(simpleUser);
//
//        assert(result);
//        verify(simpleUserRepository).save(any(SimpleUser.class));
//    }
//
//    @Test
//    public void testAddUser_UserAlreadyExists() {
//        SimpleUser simpleUser = new SimpleUser();
//        simpleUser.setEmail("simple@user.com");
//        when(simpleUserRepository.findByEmail(simpleUser.getEmail())).thenReturn(Optional.of(simpleUser));
//
//        boolean result = customUserDetailsService.addUser(simpleUser);
//
//        assert(!result);
//    }
//
//    @Test
//    public void testAddAdminUser() {
//        AdminUser adminUser = new AdminUser();
//        adminUser.setEmail("admin@user.com");
//        when(adminUserRepository.findByEmail(adminUser.getEmail())).thenReturn(Optional.empty());
//
//        boolean result = customUserDetailsService.addAdminUser(adminUser);
//
//        assert(result);
//        verify(adminUserRepository).save(any(AdminUser.class));
//    }
//
//    @Test
//    public void testAddSuperUser() {
//        SuperUser superUser = new SuperUser();
//        superUser.setEmail("superuser@user.com");
//        when(superUserRepository.findByEmail(superUser.getEmail())).thenReturn(Optional.empty());
//        when(superUserRepository.count()).thenReturn(1L);
//        when(superUserRepository.findById(1L)).thenReturn(Optional.of(new SuperUser()));
//
//        boolean result = customUserDetailsService.addSuperUser(superUser);
//
//        assert(result);
//        verify(superUserRepository).save(any(SuperUser.class));
//    }
//}
