package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.ProfilePictureNotFoundException;
import com.example.shoppingassistant.model.entity.ProfilePicture;
import com.example.shoppingassistant.repository.ProfilePictureRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ProfilePictureService {
    private static final int PROFILE_PICTURE_RANDOM_GENERATOR_LOWER_BOUND = 1;
    private static final int PROFILE_PICTURE_RANDOM_GENERATOR_UPPER_BOUND = 4;

    private final ProfilePictureRepository profilePictureRepository;

    public ProfilePictureService(ProfilePictureRepository profilePictureRepository) {
        this.profilePictureRepository = profilePictureRepository;
    }

    public void initializeGenericProfilePictures(){
        if(this.profilePictureRepository.count() == 0){
            for (int i = 1; i <= 3; i++) {

                String generatedTagNameForProfilePicture = String.format("profile%d", i);
                String resourceOfTheGenericPicture = String.format("/images/profile%d.png", i);

                ProfilePicture genericProfilePicture = new ProfilePicture(generatedTagNameForProfilePicture,resourceOfTheGenericPicture);
                this.profilePictureRepository.save(genericProfilePicture);
            }
        }
    }

    public ProfilePicture getRandomGenericPicture(){
        Random randomGenerator = new Random();
        long randomGenericProfilePictureId = randomGenerator.nextLong(PROFILE_PICTURE_RANDOM_GENERATOR_UPPER_BOUND - PROFILE_PICTURE_RANDOM_GENERATOR_LOWER_BOUND) + PROFILE_PICTURE_RANDOM_GENERATOR_LOWER_BOUND;

        return this.profilePictureRepository.findById(randomGenericProfilePictureId).orElseThrow(() -> new ProfilePictureNotFoundException("Profile picture was not found."));
    }

    public ProfilePicture saveNewProfilePicture(String imageUrl, String username) {

        ProfilePicture profilePicture = new ProfilePicture(imageUrl.substring(0,6) + username, imageUrl);
        this.profilePictureRepository.save(profilePicture);

        return profilePicture;
    }

    public void dealWithDiscardedOldPicture(ProfilePicture oldProfilePicture) {
        if(oldProfilePicture.getId() > 3){
            this.profilePictureRepository.delete(oldProfilePicture);
        }
    }
}
