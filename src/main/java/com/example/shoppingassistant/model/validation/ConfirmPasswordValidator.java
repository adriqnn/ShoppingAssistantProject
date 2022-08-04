package com.example.shoppingassistant.model.validation;

import com.example.shoppingassistant.model.service.UserServiceModel;
import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.security.Principal;


public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, String> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public ConfirmPasswordValidator(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password.isBlank()){
            return false;
        }
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        UserServiceModel userServiceModel = this.userService.findByPrincipalName(principal.getName());
        return this.passwordEncoder.matches(password,userServiceModel.getPassword());
    }
}
