package com.example.shoppingassistant.config;

import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import com.example.shoppingassistant.service.ApplicationUserDetailsService;
import com.example.shoppingassistant.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration {

    //1. PasswordEncoder;
    //2. SecurityFilterChain;
    //3. UserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers( "/", "/users/login", "/users/register").anonymous()
                .antMatchers("/", "/about", "/home", "/api/**").permitAll()
                .antMatchers("/admin/**").hasRole(UserRoleEnum.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/")
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new ApplicationUserDetailsService(userRepository);
    }
}
