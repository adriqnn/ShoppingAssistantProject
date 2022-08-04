package com.example.shoppingassistant.service;


import com.example.shoppingassistant.model.entity.User;
import com.example.shoppingassistant.model.entity.UserRole;
import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findByUsername(username).map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }

    private UserDetails map(User user){
        return new ApplicationUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(),
                user.getEmail(),user.getApplicationPoints(),user.getProfilePicture(),
                user.getRoles().stream().map(this::map).collect(Collectors.toList()));
    }

    private GrantedAuthority map(UserRole userRole){
        return new SimpleGrantedAuthority("ROLE_" + userRole.getUserRole().name());
    }
}












