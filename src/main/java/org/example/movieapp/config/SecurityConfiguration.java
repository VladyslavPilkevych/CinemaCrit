// TODO: implement

//package org.example.movieapp.config;
//
//import org.example.movieapp.service.impl.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.csrf(CsrfConfigurer::disable).authorizeHttpRequests(
//                        (auth) -> auth
//                                .requestMatchers("/*").permitAll())
//                .formLogin(login -> login.loginPage("/login").permitAll().defaultSuccessUrl("/"))
//                .logout(logout -> {
//                    logout.logoutUrl("/logout");
//                    logout.logoutSuccessUrl("/login");
//                    logout.deleteCookies("JSESSIONID");
//                    logout.invalidateHttpSession(true);
//                })
//                .authenticationProvider(daoAuthenticationProvider());
//        return http.build();
//    }
//
//    @Bean
//    DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//
//        daoAuthenticationProvider.setPasswordEncoder(bcrypPasswordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    PasswordEncoder bcrypPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}