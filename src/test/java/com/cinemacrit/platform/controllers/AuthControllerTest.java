//package com.cinemacrit.platform.controllers;
//
//import com.cinemacrit.platform.models.SimpleUser;
//import com.cinemacrit.platform.services.CustomUserDetailsService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@WebMvcTest(AuthController.class)
//class AuthControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private CustomUserDetailsService userDetailsService;
//
//    @InjectMocks
//    private AuthController authController;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
//    }
//
//    @Test
//    void testRegisterPageWhenAuthenticated() throws Exception {
//        mockMvc.perform(get("/auth/register"))
//                .andExpect(redirectedUrl("/home"));
//    }
//
//    @Test
//    void testRegisterPageWhenNotAuthenticated() throws Exception {
//        mockMvc.perform(get("/auth/register"))
//                .andExpect(view().name("register"));
//    }
//
//    @Test
//    void testRegisterPost() throws Exception {
//        SimpleUser user = new SimpleUser();
//        user.setUsername("newUser");
//        user.setPassword("password");
//
//        when(userDetailsService.addSimpleUser(user)).thenReturn(true);
//
//        mockMvc.perform(post("/auth/register")
//                        .flashAttr("simpleUser", user))
//                .andExpect(redirectedUrl("/auth/login"));
//    }
//}
