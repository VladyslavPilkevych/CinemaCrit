package org.example.movieapp.controller;

import org.example.movieapp.model.User;
import org.example.movieapp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        if (authenticationService.authenticate(user.getUsername(), user.getPassword())) {
            return "redirect:/movies";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        authenticationService.logout();
        return "redirect:/login";
    }
}
