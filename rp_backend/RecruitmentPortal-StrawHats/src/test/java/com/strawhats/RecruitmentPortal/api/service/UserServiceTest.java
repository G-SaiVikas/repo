package com.strawhats.RecruitmentPortal.api.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.repo.UserRepository;
import com.strawhats.RecruitmentPortal.service.UserService;


import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUserService() {
       User user = User.builder()
				  	.fullName("Test test")
				  	.email("test@test.com")
				  	.password("test")
				  	.phoneNumber("123456789")
				  	.build();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User registeredUser = userService.registerUser(user);

        Assertions.assertThat(registeredUser).isNotNull();
    }
    
    @Test
    public void testLoginUserService() {
    	User user = User.builder()
			  	.fullName("Test test")
			  	.email("test@test.com")
			  	.password("test")
			  	.phoneNumber("123456789")
			  	.build();
    	when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.ofNullable(user));
    	
    	User loggedUser = userService.loginUser(user.getEmail(), user.getPassword());
    	
    	Assertions.assertThat(loggedUser).isNotNull();
    }
    
    @Test
    public void testVerifyUserService() {
    	
    	User user = User.builder()
			  	.fullName("Test test")
			  	.email("test@test.com")
			  	.password("test")
			  	.phoneNumber("1234567890")
			  	.build();
    	
    	when(userRepository.findByFullNameAndPhoneNumber("Test test", "1234567890")).thenReturn(user);
    	
    	User verifiedUser = userService.verifyUser(user.getFullName(), user.getPhoneNumber());
    	Assertions.assertThat(verifiedUser).isNotNull();
//        Assertions.assertThat(verifiedUser.getFullName()).isEqualTo("Test test");
//        Assertions.assertThat(verifiedUser.getPhoneNumber()).isEqualTo("1234567890");
  	
    }
    @Test
    public void testUpdateUserService() {
    	
    	 User user = User.builder()
    	            .id(1L)
    	            .fullName("Test test")
    	            .email("test@test.com")
    	            .password("test")
    	            .phoneNumber("123456789")
    	            .build();

    	String newPassword = "newPassword";

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(1L, newPassword);

        Assertions.assertThat(updatedUser).isNotNull();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo(newPassword);

    }
}
