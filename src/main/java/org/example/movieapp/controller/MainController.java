/**
 * Controller class for handling main page redirection.
 */
package org.example.movieapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
