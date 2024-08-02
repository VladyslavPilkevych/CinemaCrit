/**
 * Controller class for handling user authentication operations.
 */
package org.example.movieapp.controller;

import org.example.movieapp.factory.PersonFactory;
import org.example.movieapp.model.User;
import org.example.movieapp.repository.UserRepository;
import org.example.movieapp.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository authenticationRepository;

    final private PersonFactory personFactory = new PersonFactory();

    /**
     * Display the login form.
     *
     * @param model Model to hold attributes for the view
     * @return The view name for the login page
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        List<User> users = authenticationService.getAllUsers();
        model.addAttribute("user", personFactory.createUser());
        model.addAttribute("usersList", users);
        return "login";
    }

    /**
     * Perform login operation.
     *
     * @param requestData JSON data containing login details
     * @return ResponseEntity with login status and message
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> requestData) {
        String operation = (String) requestData.get("operation");
        String username = (String) requestData.get("username");
        String password = (String) requestData.get("password");

        Map<String, Object> responseBody = new HashMap<>();
        if ("login".equals(operation)) {
            if (authenticationService.authenticate(username, password)) {
                boolean isAdmin = "admin".equals(username);
                responseBody.put("admin", isAdmin);
                responseBody.put("message", "Successful login");
                return ResponseEntity.ok(responseBody);
            } else {
                responseBody.put("admin", false);
                responseBody.put("message", "Invalid username or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
            }
        } else if ("signIn".equals(operation)) {
            String email = (String) requestData.get("email");
            if (authenticationService.isUserExist(username)) {
                responseBody.put("admin", false);
                responseBody.put("message", "Username already exist");
                return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(responseBody);
            } else if (authenticationService.isEmailExist(email)) {
                responseBody.put("admin", false);
                responseBody.put("message", "Email already uses");
                return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(responseBody);
            } else {
                User user = new User();
                user.setPassword(password);
                user.setUsername(username);
                user.setEmail(email);
                authenticationRepository.save(user);
                responseBody.put("admin", false);
                responseBody.put("message", "Successful login");
                return ResponseEntity.ok(responseBody);
            }
        } else {
            responseBody.put("admin", false);
            responseBody.put("message", "Invalid operation");
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    /**
     * Log out the current user.
     *
     * @return Redirect to the login page after logout
     */
    @GetMapping("/logout")
    public String logout() {
        authenticationService.logout();
        return "redirect:/login";
    }
}
