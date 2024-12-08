//package com.cinemacrit.platform.controllers;
//
//import com.cinemacrit.platform.repositories.AdminUserRepository;
//import com.cinemacrit.platform.repositories.MovieRepository;
//import com.cinemacrit.platform.repositories.SimpleUserRepository;
//import com.cinemacrit.platform.repositories.SuperUserRepository;
//import com.cinemacrit.platform.roles.Roles;
//import com.cinemacrit.platform.services.UserDetailsPrincipal;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Collections;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@ExtendWith(MockitoExtension.class)
//class HomeControllerTest {
//
//    private MockMvc mockMvc;
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
//    private MovieRepository movieRepository;
//
//    @InjectMocks
//    private HomeController homeController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
//
//        UserDetailsPrincipal mockPrincipal = Mockito.mock(UserDetailsPrincipal.class);
//        Mockito.when(mockPrincipal.getRole()).thenReturn(Roles.ADMIN);
//
//        Authentication mockAuthentication = Mockito.mock(Authentication.class);
//        Mockito.when(mockAuthentication.getPrincipal()).thenReturn(mockPrincipal);
//
//        SecurityContext mockSecurityContext = Mockito.mock(SecurityContext.class);
//        Mockito.when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);
//
//        SecurityContextHolder.setContext(mockSecurityContext);
//
//        Mockito.when(simpleUserRepository.findAll()).thenReturn(Collections.emptyList());
//        Mockito.when(adminUserRepository.findAll()).thenReturn(Collections.emptyList());
//        Mockito.when(superUserRepository.findAll()).thenReturn(Collections.emptyList());
//        Mockito.when(movieRepository.findAll()).thenReturn(Collections.emptyList());
//    }
//
//    @Disabled("Test under development")
//    @Test
//    void testHomePageWhenAuthenticated() throws Exception {
//        mockMvc.perform(get("/home"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("home"));
//    }
//}
