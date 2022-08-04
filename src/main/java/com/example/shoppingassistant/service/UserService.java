package com.example.shoppingassistant.service;

import com.example.shoppingassistant.event.AddedProductByUserEvent;
import com.example.shoppingassistant.exceptions.UserNotFoundException;
import com.example.shoppingassistant.model.entity.ProfilePicture;
import com.example.shoppingassistant.model.entity.User;
import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import com.example.shoppingassistant.model.service.UserServiceModel;
import com.example.shoppingassistant.model.view.UserViewModel;
import com.example.shoppingassistant.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final int USER_POINTS_INCREASE = 1;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final UserRoleService userRoleService;
    private final ProfilePictureService profilePictureService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, UserRoleService userRoleService, ProfilePictureService profilePictureService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.userRoleService = userRoleService;
        this.profilePictureService = profilePictureService;
    }

    public String register(UserServiceModel userServiceModel){

        Optional<User> byName = this.userRepository.findByUsername(userServiceModel.getUsername());
        Optional<User> byEmail = this.userRepository.findByEmail(userServiceModel.getEmail());

        if(byName.isEmpty() && byEmail.isEmpty()){
            ProfilePicture genericProfilePicture = this.profilePictureService.getRandomGenericPicture();

            User user = this.modelMapper.map(userServiceModel, User.class);
            user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
            user.setApplicationPoints(0);
            user.setProfilePicture(genericProfilePicture.getLocation());
            user.getRoles().add(userRoleService.getUserRole(UserRoleEnum.USER));
            user.setProfilePictureClass(genericProfilePicture);
            this.userRepository.save(user);
            return "userSaved";
        }else if(byName.isPresent() && byEmail.isPresent()){
            return "bothUsernameAndEmailAreTaken";
        }else if(byName.isPresent()){
            return "usernameTaken";
        }else {
            return "emailTaken";
        }
    }

    public void login(UserServiceModel userServiceModel){
        User user = this.userRepository.findByUsername(userServiceModel.getUsername()).orElseThrow(() -> new UserNotFoundException("User was not found."));
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public void login(User user){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public void createAdminAccount(){
        if(this.userRepository.count() == 0){
            ProfilePicture genericProfilePicture = this.profilePictureService.getRandomGenericPicture();

            User admin = new User("Administrator", "admin123", "admin", "admin@shoppingassistant.com",0,genericProfilePicture.getLocation());
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            admin.getRoles().add(userRoleService.getUserRole(UserRoleEnum.ADMIN));
            admin.getRoles().add(userRoleService.getUserRole(UserRoleEnum.USER));
            admin.setProfilePictureClass(genericProfilePicture);
            this.userRepository.save(admin);
        }
    }

    public User findByName(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User was not found."));
    }

    public void saveChanges(User user) {
        this.userRepository.save(user);
    }

    public UserViewModel getUserViewModel(Long id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User was not found."));
        return this.modelMapper.map(user, UserViewModel.class);
    }

    public UserServiceModel findByPrincipalName(String name){
        User user = userRepository.findByUsername(name).orElseThrow(() -> new UserNotFoundException("User was not found."));
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    public void saveUsernameChanges(UserServiceModel userServiceModel, Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User was not found."));
        user.setUsername(userServiceModel.getUsername());
        this.userRepository.save(user);

        login(user);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void savePasswordChanges(String newPassword, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User was not found."));
        user.setPassword(passwordEncoder.encode(newPassword));
        this.userRepository.save(user);

        login(user);
    }

    public void saveFirstNameChanges(UserServiceModel userServiceModel, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User was not found."));
        user.setFirstName(userServiceModel.getFirstName());
        this.userRepository.save(user);

        login(user);
    }

    public void saveEmailChanges(String email, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User was not found."));
        user.setEmail(email);
        this.userRepository.save(user);

        login(user);
    }

    public void saveProfilePictureChanges(String imageUrl, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User was not found."));
        ProfilePicture oldProfilePicture = user.getProfilePictureClass();
        ProfilePicture profilePicture = this.profilePictureService.saveNewProfilePicture(imageUrl,user.getUsername());
        user.setProfilePicture(profilePicture.getLocation());
        user.setProfilePictureClass(profilePicture);
        this.profilePictureService.dealWithDiscardedOldPicture(oldProfilePicture);
        this.userRepository.save(user);

        login(user);
    }

    public List<String> getAllUsersUsernames(){
        return this.userRepository.getAllUsersUsernames();
    }

    public long getCountOfAllUsers() {
        return this.userRepository.count();
    }

    public User getById(Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User was not found."));
    }

    @EventListener(AddedProductByUserEvent.class)
    public void onAddedProductByUserAddBonusPointsToUserAccount(AddedProductByUserEvent event){
        User user = this.userRepository.findById(event.getUserId()).orElseThrow(() -> new UserNotFoundException("User was not found."));
        user.setApplicationPoints(user.getApplicationPoints() + USER_POINTS_INCREASE);
        userRepository.save(user);
    }
}































