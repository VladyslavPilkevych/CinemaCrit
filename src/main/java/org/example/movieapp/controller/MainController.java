package org.example.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    /**
     * Redirect to the movies page.
     *
     * @return The view name for the movies page
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/movies";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring Boot!";
    }
}
