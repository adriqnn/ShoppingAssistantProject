package com.example.shoppingassistant.model.view;

public class UserViewModel {

    private Long id;
    private String username;
    private String firstName;
    private String email;
    private int applicationPoints;
    private String profilePicture;

    public UserViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getApplicationPoints() {
        return applicationPoints;
    }

    public UserViewModel setApplicationPoints(int applicationPoints) {
        this.applicationPoints = applicationPoints;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public UserViewModel setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }
}
