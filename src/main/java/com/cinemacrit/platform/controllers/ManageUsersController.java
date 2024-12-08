package com.cinemacrit.platform.controllers;

import com.cinemacrit.platform.models.User;
import com.cinemacrit.platform.repositories.*;
import com.cinemacrit.platform.roles.Roles;
import com.cinemacrit.platform.services.CustomUserDetailsService;
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
import java.util.List;

/**
 * Controller class for managing users and movies by admins and superusers.
 */
@Controller
@RequestMapping("/admin")
public class ManageUsersController {
    /**
     * Default constructor for the ManageUsersController.
     */
    public ManageUsersController() {}

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    SimpleUserRepository simpleUserRepository;
    @Autowired
    AdminUserRepository adminUserRepository;
    @Autowired
    SuperUserRepository superUserRepository;
    @Autowired
    MovieRepository movieRepository;

    static final List<User> usersToBlock = new ArrayList<>();

    /**
     * Displays the admin home page with user and movie statistics.
     *
     * @param model Model object to add attributes for the view.
     * @return View name for the admin home page.
     */
    @GetMapping("")
    public String adminHome(Model model){
        UserDetailsPrincipal userDetailsPrincipal = (UserDetailsPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Roles role = userDetailsPrincipal.getRole();

        // Add user and movies statistics to the model
        model.addAttribute("simpleUsersCount", simpleUserRepository.count());
        model.addAttribute("adminUsersCount", adminUserRepository.count());
        model.addAttribute("superUsersCount", superUserRepository.count());
        model.addAttribute("movieCount", movieRepository.count());

        // Add users to the model based on the role
        if(usersToBlock.isEmpty()){
            switch(role){
                case ADMIN -> model.addAttribute("users", simpleUserRepository.findAll());
                case SUPER -> {
                    List<User> users = new ArrayList<>(simpleUserRepository.findAll());
                    users.addAll(adminUserRepository.findAll());
                    if(userDetailsPrincipal.getIsRoot()){
                        users.addAll(superUserRepository.findAllByIsRootIsFalse());
                    }
                    model.addAttribute("users", users);
                }
                default -> model.addAttribute("users", new ArrayList<User>());
            }
        }else{
            model.addAttribute("users", new ArrayList<>(usersToBlock));
            usersToBlock.clear();
        }

        // Add movies to the model
        model.addAttribute("movies", movieRepository.findAll());

        return "admin-home";
    }

    /**
     * Displays the user addition form.
     *
     * @param model Model object to add attributes for the view.
     * @return View name for the user addition form.
     */
    @GetMapping("/add")
    public String addUser(Model model){
        model.addAttribute(new User());
        return "super-add";
    }

    /**
     * Adds a new user.
     *
     * @param user User object containing user data.
     * @return Redirects to the user addition form with success or failure message.
     */
    @PostMapping("/add")
    public String addUser(User user){
        return customUserDetailsService.addUser(user) ? "redirect:/admin/add?success" : "redirect:/admin/add?failure";
    }

    /**
     * Searches for users and adds them to the block list based on the search query.
     *
     * @param search Search query for finding users.
     * @return Redirects to the admin home page.
     */
    @GetMapping("/block/users")
    public String blockUser(@RequestParam(name = "search", defaultValue = "")String search){
        UserDetailsPrincipal userDetailsPrincipal = (UserDetailsPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Roles role = userDetailsPrincipal.getRole();

        usersToBlock.clear();

        switch(role){
            case ADMIN -> usersToBlock.addAll(simpleUserRepository.findByUsernameContaining(search));
            case SUPER -> {
                usersToBlock.addAll(simpleUserRepository.findByUsernameContaining(search));
                usersToBlock.addAll(adminUserRepository.findByUsernameContaining(search));
                if(userDetailsPrincipal.getIsRoot()){
                    usersToBlock.addAll(superUserRepository.findAllByUsernameContainingAndIsRootIsFalse(search));
                }
            }
        }

        return "redirect:/admin";
    }

    /**
     * Blocks users based on their email addresses.
     *
     * @param email Email address of the user to be blocked.
     * @return Redirects to the admin home page.
     */
    @PostMapping("/block/users")
    public String blockUsers(@RequestParam(name = "email") String email){
        simpleUserRepository.findByEmail(email).ifPresentOrElse(user -> {
            user.setEnabled(false);
            simpleUserRepository.save(user);
        }, () -> adminUserRepository.findByEmail(email).ifPresentOrElse(user -> {
            user.setEnabled(false);
            adminUserRepository.save(user);
        }, () -> {
                    if (((UserDetailsPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getIsRoot()) {
                        superUserRepository.findByEmail(email).ifPresentOrElse(user -> {
                            user.setEnabled(false);
                            superUserRepository.save(user);
                        }, () -> {
                        });
                    }
                }
        ));

        return "redirect:/admin";
    }

    /**
     * Unblocks users based on their email addresses.
     *
     * @param email Email address of the user to be unblocked.
     * @return Redirects to the admin home page.
     */
    @PostMapping("/unblock/users")
    public String unblockUsers(@RequestParam(name = "email") String email){
        simpleUserRepository.findByEmail(email).ifPresentOrElse(user -> {
            user.setEnabled(true);
            simpleUserRepository.save(user);
        }, () -> adminUserRepository.findByEmail(email).ifPresentOrElse(user -> {
            user.setEnabled(true);
            adminUserRepository.save(user);
        }, () -> superUserRepository.findByEmail(email).ifPresentOrElse(user -> {
            user.setEnabled(true);
            superUserRepository.save(user);
        }, () -> {})));

        return "redirect:/admin";
    }
}