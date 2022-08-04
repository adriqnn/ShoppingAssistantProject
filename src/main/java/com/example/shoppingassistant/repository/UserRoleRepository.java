package com.example.shoppingassistant.repository;

import com.example.shoppingassistant.model.entity.UserRole;
import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByUserRole(UserRoleEnum userRoleEnum);
}
