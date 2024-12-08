package com.cinemacrit.platform.services;

import com.cinemacrit.platform.models.*;
import com.cinemacrit.platform.ooprequirements.Actions;
import com.cinemacrit.platform.ooprequirements.CreateUserActionListener;
import com.cinemacrit.platform.ooprequirements.EventManager;
import com.cinemacrit.platform.repositories.AdminUserRepository;
import com.cinemacrit.platform.repositories.SimpleUserRepository;
import com.cinemacrit.platform.repositories.SuperUserRepository;
import com.cinemacrit.platform.roles.Roles;
import com.cinemacrit.platform.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Service class for managing custom user-related operations such as user creation,
 * authentication, and role assignment.
 *
 * Implements the {@link UserDetailsService} interface to load users based on their email.
 * Handles user creation and ensures the initialization of a root super user if one doesn't exist.
 *
 * This class also interacts with repositories to manage different types of users (simple, admin, super),
 * and provides methods for adding new users based on their roles.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Default constructor for the CustomUserDetailsService class.
     */
    public CustomUserDetailsService() {}

    @Autowired
    private SimpleUserRepository simpleUserSimpleUserRepository;
    @Autowired
    private SuperUserRepository superUserRepository;
    @Autowired
    private AdminUserRepository adminUserUserRepository;

    private static final EventManager eventManager;
    static{
        eventManager = EventManager.getInstance();
        eventManager.subscribe(Actions.CREATE_USER, new CreateUserActionListener());
    }

    /**
     * Loads a user by their email address.
     *
     * This method is used for authentication. It searches for the user in the Simple, Admin, or Super user repositories,
     * and returns a {@link UserDetailsPrincipal} if the user is found.
     *
     * @param email the email address of the user to be loaded.
     * @return a {@link UserDetailsPrincipal} object containing the user's details.
     * @throws UsernameNotFoundException if the user with the specified email address is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserDetailsPrincipal[] userDetailsPrincipal = new UserDetailsPrincipal[1];

        simpleUserSimpleUserRepository.findByEmail(email).ifPresentOrElse(
                user -> userDetailsPrincipal[0] =  new UserDetailsPrincipal(user),
                        () -> adminUserUserRepository.findByEmail(email).ifPresentOrElse(
                                user -> userDetailsPrincipal[0] = new UserDetailsPrincipal(user),
                                () -> superUserRepository.findByEmail(email).ifPresentOrElse(
                                        user -> userDetailsPrincipal[0] = new UserDetailsPrincipal(user),
                                        () -> {throw new UsernameNotFoundException("Couldn't find user with the email: " + email);}
                                )
                        )
        );
        return userDetailsPrincipal[0];
    }

    /**
     * Initializes the root super user if none exists.
     *
     * This method checks if a super user exists in the system, and if not, creates a new super user with default credentials
     * and saves them to the database. The super user's credentials are also written to a file named "credentials.txt".
     */
    @Bean
    public void rootSuperUserPersist(){
        if(superUserRepository.count() == 0L){
            SuperUser superUser = new SuperUser();
            superUser.setUsername("super user");
            superUser.setEmail("super@user.com");
            superUser.setRoot(true);
            superUser.setEnabled(true);
            String password = PasswordGenerator.generatePassword(16);
            superUser.setPassword(password);
            superUser.setRole(Roles.SUPER);
            superUser.setCreatedAt(LocalDateTime.now());
            superUser.setCreatedAt(superUser.getCreatedAt());
            superUserRepository.save(superUser);

            System.out.println("Super user credentials: " + superUser.getEmail() + " " + password);

            File file = new File("credentials.txt");
            try(BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file)); ObjectOutputStream obs = new ObjectOutputStream(out)){
                obs.writeObject(superUser);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }else{
            File file = new File("credentials.txt");
            if(file.exists()){
                try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))){
                    SuperUser superUser = (SuperUser) ois.readObject();
                    superUserRepository.findByEmail(superUser.getEmail()).ifPresent(user -> {
                        if(user.equals(superUser) && !user.getRoot()){
                            superUserRepository.delete(user);
                            file.delete();
                        }
                    });
                }catch(IOException | ClassNotFoundException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    /**
     * Adds a new user based on the user's role.
     *
     * The method checks if a user with the specified email already exists. If not, it adds a new user
     * to the system based on their role (simple user, admin, or super).
     *
     * @param user the user to be added.
     * @return true if the user is successfully added, false otherwise.
     * @throws NullPointerException if the user is null.
     */
    public boolean addUser(User user) throws NullPointerException {
        try{
            loadUserByUsername(user.getEmail());
        }catch(UsernameNotFoundException ex){
            return switch(user.getRole()){
                case Roles.USER -> addSimpleUser(user);
                case Roles.ADMIN -> addAdminUser(user);
                case Roles.SUPER -> addSuperUser(user);
            };
        }
        return false;
    }

    /**
     * Adds a simple user to the system.
     *
     * The method checks if a simple user with the given email already exists. If not, it sets up default user properties,
     * saves the user to the repository, and returns true. Otherwise, it returns false.
     *
     * @param user the simple user to be added.
     * @return true if the simple user is successfully added, false otherwise.
     * @throws NullPointerException if the user is null.
     */
    public boolean addSimpleUser(User user) throws NullPointerException {
        if(simpleUserSimpleUserRepository.findByEmail((user.getEmail())).isEmpty()){
            setupDefaultUser(user);
            simpleUserSimpleUserRepository.save(new SimpleUser(user));
            return true;
        }
        return false;
    }

    /**
     * Adds an admin user to the system.
     *
     * Similar to adding a simple user, but with an admin role. If the admin user does not exist, it sets up default properties,
     * saves the user to the repository, and returns true. If the user already exists, it returns false.
     *
     * @param user the admin user to be added.
     * @return true if the admin user is successfully added, false otherwise.
     * @throws NullPointerException if the user is null.
     */
    public boolean addAdminUser(User user) throws NullPointerException {
        if(adminUserUserRepository.findByEmail(user.getEmail()).isEmpty()){
            setupDefaultUser(user);
            user.setRole(Roles.ADMIN);
            adminUserUserRepository.save(new AdminUser(user));

            return true;
        }
        return false;
    }

    /**
     * Adds a super user to the system.
     *
     * Similar to adding other users, but with special handling for the super user. If a super user does not exist,
     * it creates the user, sets up default properties, and ensures that the first super user is set as the root super user.
     *
     * @param user the super user to be added.
     * @return true if the super user is successfully added, false otherwise.
     * @throws NullPointerException if the user is null.
     */
    public boolean addSuperUser(User user) throws NullPointerException {
        if(superUserRepository.findByEmail(user.getEmail()).isEmpty()) {
            SuperUser superUser = new SuperUser(user);
            if(superUserRepository.count() == 1L && superUserRepository.findById(1L).isPresent()){
                superUser.setRoot(true);
                SuperUser superUser1 = superUserRepository.findById(1L).get();
                superUser1.setRoot(false);
                superUserRepository.save(superUser1);
            }
            setupDefaultUser(superUser);
            superUser.setRole(Roles.SUPER);
            superUserRepository.save(superUser);
            return true;
        }
        return false;
    }

    /**
     * Sets up default properties for a new user.
     *
     * This method sets the user's role, enables the user, and triggers an event for user creation.
     *
     * @param user the user to be set up.
     */
    private void setupDefaultUser(User user){
        eventManager.notify(Actions.CREATE_USER,
                SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken ?
                        user.getEmail() :
                        ((UserDetailsPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail()
                , user.getEmail());

        user.setRole(Roles.USER);
        user.setEnabled(true);
    }
}