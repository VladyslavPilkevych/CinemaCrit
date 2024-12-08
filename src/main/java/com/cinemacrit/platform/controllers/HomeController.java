package com.cinemacrit.platform.controllers;

import com.cinemacrit.platform.models.Movie;
import com.cinemacrit.platform.models.User;
import com.cinemacrit.platform.repositories.AdminUserRepository;
import com.cinemacrit.platform.repositories.MovieRepository;
import com.cinemacrit.platform.repositories.SimpleUserRepository;
import com.cinemacrit.platform.repositories.SuperUserRepository;
import com.cinemacrit.platform.roles.Roles;
import com.cinemacrit.platform.services.UserDetailsPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * Controller class for handling home-related endpoints.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    /**
     * Default constructor for the HomeController.
     */
    public HomeController() {}

    @Autowired
    private SimpleUserRepository simpleUserRepository;
    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private SuperUserRepository superUserRepository;
    @Autowired
    private MovieRepository movieRepository;

    static List<Movie> foundedMovies = new ArrayList<>();

    static final List<User> foundedUsers = new ArrayList<>();

    /**
     * Handles GET requests for the home page.
     *
     * @param model Model object to add attributes for the view.
     * @return View name for the home page.
     */
    @GetMapping("")
    public String home(Model model) {
        UserDetailsPrincipal userDetailsPrincipal = (UserDetailsPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Roles role = userDetailsPrincipal.getRole();

        // Add users to model
        if(foundedUsers.isEmpty()){
            List<User> users = new ArrayList<>(simpleUserRepository.findAll());
            users.addAll(adminUserRepository.findAll());
            users.addAll(superUserRepository.findAll());
            model.addAttribute("users", users);
        }else{
            model.addAttribute("users", new ArrayList<>(foundedUsers));
        }
        model.addAttribute("movies", foundedMovies.isEmpty() ?  movieRepository.findAll() : new ArrayList<>(foundedMovies));

        if (userDetailsPrincipal.getRole() == Roles.ADMIN || userDetailsPrincipal.getRole() == Roles.SUPER) {
            model.addAttribute("movie", new Movie());
        }

        return "home";
    }

    /**
     * Handles POST requests for retracting movies.
     *
     * @param id Movie ID.
     * @return Redirect to the home page.
     */
    @PostMapping("/retract")
    public String home(@RequestParam(name = "id")UUID id){
        UserDetailsPrincipal userDetailsPrincipal = (UserDetailsPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetailsPrincipal.getRole() == Roles.USER) {
            simpleUserRepository.findById(userDetailsPrincipal.getId()).ifPresent(
                    user -> {
                        simpleUserRepository.save(user);
                    }
            );
        }
        return "redirect:/home";
    }

    /**
     * Handles GET requests for searching users.
     *
     * @param search Search query.
     * @return Redirect to the home page.
     */
    @GetMapping("/users")
    public String searchUsers(@RequestParam(name="search", defaultValue = "") String search){
        foundedUsers.clear();
        foundedUsers.addAll(simpleUserRepository.findByUsernameContaining(search));
        foundedUsers.addAll(adminUserRepository.findByUsernameContaining(search));
        foundedUsers.addAll(superUserRepository.findByUsernameContaining(search));
        foundedUsers.sort(Comparator.comparing(a -> a.getUsername().toUpperCase()));
        return "redirect:/home";
    }

    /**
     * Handles GET requests for searching movies.
     *
     * @param search Search query.
     * @return Redirect to the home page.
     */
    @GetMapping("/movies")
    public String movies(@RequestParam(name = "search", defaultValue = "") String search) {
        foundedMovies.clear();
        foundedMovies.addAll(movieRepository.findByTitleContaining(search));
        foundedMovies.addAll(movieRepository.findByDescriptionContaining(search));
        foundedMovies = new ArrayList<>(foundedMovies.stream().sorted().distinct().toList());
        return "redirect:/home";
    }
}