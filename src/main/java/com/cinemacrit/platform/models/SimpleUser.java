package com.cinemacrit.platform.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Represents a simple user in the system.
 * This class extends the User class and inherits its properties and methods.
 * It also adds a new property for storing the user's avatar image.
 *
 * The SimpleUser class is a JPA entity and is mapped to a database table that stores user-related information.
 * It can be used to represent regular users in the system who may have additional attributes compared to
 * more advanced user types.
 */
@Entity
public class SimpleUser extends User {

    /**
     * Default constructor for the SimpleUser class.
     * Initializes a new instance of the SimpleUser with default values.
     */
    public SimpleUser() {}

    /**
     * Constructor for the SimpleUser class that initializes it with properties from another User object.
     * This constructor allows creating a SimpleUser instance with the same properties as an existing User.
     *
     * @param user Another User object whose properties are used to initialize the SimpleUser.
     */
    public SimpleUser(User user) {
        super(user);
    }

    /**
     * The avatar of the user, stored as a Base64-encoded string.
     * This represents the profile image of the user.
     *
     */
    @Column(columnDefinition = "BLOB", nullable = true)
    private String avatar;

    /**
     * Gets the avatar image of the user.
     *
     * @return the Base64-encoded string representing the user's avatar image.
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the avatar image of the user.
     * The avatar is stored as a Base64-encoded string.
     *
     * @param avatar the Base64-encoded string representing the user's avatar image.
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
