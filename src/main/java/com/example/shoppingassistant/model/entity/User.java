package com.example.shoppingassistant.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "application_points", nullable = false)
    private int applicationPoints;

    @Column(name = "profile_picture", nullable = false)
    private String profilePicture;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRole> roles;

    @ManyToMany
    private List<Product> products;

    @ManyToOne
    private ProfilePicture profilePictureClass;

    public User() {
        this.roles = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public User(Long id, String username, String password, String firstName, String email, int applicationPoints, String profilePicture) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.email = email;
        this.applicationPoints = applicationPoints;
        this.profilePicture = profilePicture;
        this.roles = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public User(String username, String password, String firstName, String email, int applicationPoints, String profilePicture) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.email = email;
        this.applicationPoints = applicationPoints;
        this.profilePicture = profilePicture;
        this.roles = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getApplicationPoints() {
        return applicationPoints;
    }

    public User setApplicationPoints(int applicationPoints) {
        this.applicationPoints = applicationPoints;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public User setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public User setRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public User setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public ProfilePicture getProfilePictureClass() {
        return profilePictureClass;
    }

    public User setProfilePictureClass(ProfilePicture profilePictureClass) {
        this.profilePictureClass = profilePictureClass;
        return this;
    }
}
