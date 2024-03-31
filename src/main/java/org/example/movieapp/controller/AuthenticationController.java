package org.example.movieapp.controller;

import org.example.movieapp.model.User;
import org.example.movieapp.repository.UserRepository;
import org.example.movieapp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;


@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository authenticationRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        List<User> users = authenticationService.getAllUsers();
        model.addAttribute("user", new User());
        model.addAttribute("usersList", users);
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, Object> requestData) {
//    public ResponseEntity<String> login(@ModelAttribute("user") User user) {
//        if (authenticationService.authenticate(user.getUsername(), user.getPassword())) {
//            return "redirect:/movies";
//        } else {
//            return "redirect:/login?error";
//        }
        String operation = (String) requestData.get("operation");
        String username = (String) requestData.get("username");
        String password = (String) requestData.get("password");
        if ("login".equals(operation)) {

            if (authenticationService.authenticate(username, password)) {
                return ResponseEntity.ok("Successful login");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } else if ("signIn".equals(operation)) {
            String email = (String) requestData.get("email");
            if (authenticationService.authenticate(username, password)) {
                return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("Invalid username or password");
            } else {
                User user = new User();
                user.setPassword(password);
                user.setUsername(username);
                user.setEmail(email);
                authenticationRepository.save(user);
                return ResponseEntity.ok("Successful login");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid operation");
        }
    }

//    @PostMapping("/signin")
//    public ResponseEntity<String> signIn(@ModelAttribute("user") User user) {
//        System.out.printf("@PostMapping(\"/signin\")");
//        if (authenticationService.authenticate(user.getUsername(), user.getPassword())) {
//            return ResponseEntity.status(HttpStatus.MULTI_STATUS).body("Invalid username or password");
//        } else {
//            user.setId(null);
//            authenticationRepository.save(user);
//            return ResponseEntity.ok("Successful login");
//        }
//    }

    @GetMapping("/logout")
    public String logout() {
        authenticationService.logout();
        return "redirect:/login";
    }
}
