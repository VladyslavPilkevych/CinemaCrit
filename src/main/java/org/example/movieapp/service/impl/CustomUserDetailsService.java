// TODO: implement

//package org.example.movieapp.service.impl;
//
//import org.example.movieapp.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        final CustomUserDetails customUserDetails = new CustomUserDetails();
//        return (UserDetails) userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
////        .ifPresent(user -> {
////            return user;
////        });
////        return customUserDetails;
////        return userRepository.findByUsername(username)
////                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//    }
//}