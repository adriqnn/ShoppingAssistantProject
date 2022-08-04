package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.UserRoleNotFoundException;
import com.example.shoppingassistant.model.entity.UserRole;
import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import com.example.shoppingassistant.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void initializeUserRoles(){
        if(this.userRoleRepository.count() == 0){

            UserRole admin =  new UserRole(UserRoleEnum.ADMIN);
            UserRole user = new UserRole(UserRoleEnum.USER);

            this.userRoleRepository.save(admin);
            this.userRoleRepository.save(user);
        }
    }

    public UserRole getUserRole(UserRoleEnum userRoleEnum){
        return this.userRoleRepository.findByUserRole(userRoleEnum).orElseThrow(() -> new UserRoleNotFoundException("User role was not found."));
    }
}
