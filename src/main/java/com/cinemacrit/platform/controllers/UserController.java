package com.cinemacrit.platform.controllers;

import com.cinemacrit.platform.models.Movie;
import com.cinemacrit.platform.models.Review;
import com.cinemacrit.platform.models.SimpleUser;
import com.cinemacrit.platform.repositories.MovieRepository;
import com.cinemacrit.platform.repositories.ReviewRepository;
import com.cinemacrit.platform.repositories.SimpleUserRepository;
import com.cinemacrit.platform.services.UserDetailsPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller class for managing user-related actions such as profile management,
 * changing user information (avatar, name, email, password), and account deletion.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    SimpleUserRepository simpleUserRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MovieRepository movieRepository;

    /**
     * Default constructor.
     * This constructor is automatically provided by Spring Framework for dependency injection.
     */
    public UserController() {}

    /**
     * Displays the user profile page with the user's details and reviews.
     * Retrieves the user's profile information, associated reviews, and movie titles
     * and adds them to the model for rendering in the view.
     *
     * @param model the model to be used by the view
     * @return the name of the view to be rendered ("user-profile")
     */
    @GetMapping("/profile")
    public String getProfile(Model model) {
        UserDetailsPrincipal principal = (UserDetailsPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SimpleUser user = simpleUserRepository.findById(principal.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Review> reviews = reviewRepository.findReviewsByUsername(user.getUsername()).map(List::of).orElse(Collections.emptyList());;

        Map<Review, String> reviewsWithMovieTitles = reviews.stream()
                .collect(Collectors.toMap(
                        review -> review,
                        review -> movieRepository.findById(review.getMovieId())
                                .map(Movie::getTitle)
                                .orElse("Unknown Movie")
                ));

        model.addAttribute("user", user);
        model.addAttribute("reviews", reviewsWithMovieTitles);

        return "user-profile";
    }

    /**
     * Handles the upload of a new avatar image for the user.
     * The uploaded avatar image is converted to a Base64 string and stored in the user's profile.
     *
     * @param avatar the avatar image file uploaded by the user
     * @return a redirect URL to the user's profile page after the avatar is updated
     */
    @PostMapping("/upload-avatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile avatar) {
        try {
            if (!avatar.isEmpty()) {
                UserDetailsPrincipal principal = (UserDetailsPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                SimpleUser user = simpleUserRepository.findById(principal.getId()).orElseThrow(() -> new RuntimeException("User not found"));

                String base64Avatar = "data:" + avatar.getContentType() + ";base64," + Base64.getEncoder().encodeToString(avatar.getBytes());
                user.setAvatar(base64Avatar);
                simpleUserRepository.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/profile";
    }

    /**
     * Handles the update of the user's username.
     * The new username is stored in the user's profile and updated in the database.
     *
     * @param name the new username provided by the user
     * @return a redirect URL to the user's profile page after the username is updated
     */
    @PostMapping("/change-name")
    public String changeName(@RequestParam("name") String name) {
        UserDetailsPrincipal principal = (UserDetailsPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SimpleUser user = simpleUserRepository.findById(principal.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(name);
        simpleUserRepository.save(user);

        return "redirect:/profile";
    }

    /**
     * Handles the update of the user's email address.
     * The new email is stored in the user's profile and updated in the database.
     *
     * @param email the new email address provided by the user
     * @return a redirect URL to the user's profile page after the email is updated
     */
    @PostMapping("/change-email")
    public String changeEmail(@RequestParam("email") String email) {
        UserDetailsPrincipal principal = (UserDetailsPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SimpleUser user = simpleUserRepository.findById(principal.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(email);
        simpleUserRepository.save(user);

        return "redirect:/profile";
    }

    /**
     * Handles the update of the user's password.
     * The current password is verified, and if correct, the user's password is updated.
     *
     * @param currentPassword the user's current password for verification
     * @param newPassword the new password to be set for the user
     * @return a redirect URL to the user's profile page after the password is updated
     * @throws RuntimeException if the current password is incorrect
     */
    @PostMapping("/change-password")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword) {
        UserDetailsPrincipal principal = (UserDetailsPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SimpleUser user = simpleUserRepository.findById(principal.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!new BCryptPasswordEncoder().matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        user.setPassword(newPassword);
        simpleUserRepository.save(user);

        return "redirect:/profile";
    }

    /**
     * Handles the deletion of the user's account.
     * All reviews associated with the user are updated to reflect the deletion, and the user is removed from the database.
     * The user is then logged out, and redirected to the login page with an account deletion message.
     *
     * @return a redirect URL to the login page after the account is deleted
     */
    @PostMapping("/delete")
    public String deleteAccount() {
        UserDetailsPrincipal principal = (UserDetailsPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        Long userId = principal.getId();

        reviewRepository.updateUsernameForReview(username, "Deleted user");

        simpleUserRepository.deleteById(userId);

        SecurityContextHolder.clearContext();
        return "redirect:/auth/login?accountDeleted";
    }
}
