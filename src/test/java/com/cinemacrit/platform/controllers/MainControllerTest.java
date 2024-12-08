//package com.cinemacrit.platform.controllers;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class MainControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private SecurityContext securityContext;
//
//    @Mock
//    private Authentication authentication;
//
//    private MainController mainController;
//
//    @BeforeEach
//    public void setUp() {
//        mainController = new MainController();
//        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
//    }
//
//    @Test
//    public void testIndex_UnauthenticatedUser() throws Exception {
//        authentication = mock(AnonymousAuthenticationToken.class);
//        SecurityContextHolder.setContext(securityContext);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.view().name("about"));
//    }
//
//    @Test
//    public void testIndex_AuthenticatedUser() throws Exception {
//        authentication = mock(Authentication.class);
//        SecurityContextHolder.setContext(securityContext);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        when(authentication instanceof AnonymousAuthenticationToken).thenReturn(false);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
//    }
//}
