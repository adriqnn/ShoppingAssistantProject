package com.example.shoppingassistant.model.entity;

import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRole;

    public UserRole() {
    }

    public UserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }

    public UserRole(Long id, UserRoleEnum userRole) {
        this.id = id;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public UserRole setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public UserRole setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }
}
