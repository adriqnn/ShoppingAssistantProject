package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.ProductPictureNotFoundException;
import com.example.shoppingassistant.exceptions.ProfilePictureNotFoundException;
import com.example.shoppingassistant.model.entity.ProfilePicture;
import com.example.shoppingassistant.repository.ProfilePictureRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfilePictureServiceTest {

    @Mock
    private ProfilePictureRepository profilePictureRepository;

    private ProfilePictureService profilePictureService;

    @BeforeEach
    void setUp(){
        profilePictureService = new ProfilePictureService(profilePictureRepository);
    }

    @Test
    void testGetRandomGenericPicture_throwError(){

        Assertions.assertThrows(ProfilePictureNotFoundException.class, () -> this.profilePictureService.getRandomGenericPicture());

    }

    @Test
    void testSaveNewProfilePicture_returnProfilePicture(){

        Assertions.assertEquals("test.puser1",this.profilePictureService.saveNewProfilePicture("test.png", "user1").getTag());
        Assertions.assertEquals("test.puser2",this.profilePictureService.saveNewProfilePicture("test.png","user2").getTag());
        Assertions.assertEquals("test.puser3",this.profilePictureService.saveNewProfilePicture("test.png","user3").getTag());

    }
}
