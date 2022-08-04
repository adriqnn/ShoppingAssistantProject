package com.example.shoppingassistant.model.userDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ApplicationUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final String firstName;
    private final String email;
    private final int applicationPoints;
    private final String profilePicture;
    private final Collection<GrantedAuthority> authorities;

    public ApplicationUserDetails(Long id, String username, String password, String firstName, String email,
                                  int applicationPoints, String profilePicture, Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.email = email;
        this.applicationPoints = applicationPoints;
        this.profilePicture = profilePicture;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public int getApplicationPoints() {
        return applicationPoints;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
