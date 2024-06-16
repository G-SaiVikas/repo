package com.strawhats.RecruitmentPortal.api.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.strawhats.RecruitmentPortal.dto.JobDTO;
import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.repo.JobRepository;
import com.strawhats.RecruitmentPortal.repo.UserRepository;
import com.strawhats.RecruitmentPortal.service.JobService;
import com.strawhats.RecruitmentPortal.service.UserService;


import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    
    @Mock
    private JobRepository jobRepository;
    
    @InjectMocks
    private JobService jobService;

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
    
    @Test
    public void testSaveJob() {
    	
    	User user = User.builder()
	            .id(1L)
	            .fullName("Test test")
	            .email("test@test.com")
	            .password("test")
	            .phoneNumber("123456789")
	            .savedJobs(new ArrayList<>())
	            .build();
    	
    	Job job = Job.builder()
    			.id(1L)
				.company("Test Company")
				.jobDescription("Test description")
				.jobTitle("Test title")
				.skillsRequired("Test1, test2, test3")
				.location("test city")
				.savedJobsApplicants(new ArrayList<>())
				.salary("10000").build();
    	
    	when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        userService.saveJob(1L, 1L);

        Assertions.assertThat(user.getSavedJobs()).contains(job);
        Assertions.assertThat(job.getSavedJobsApplicants()).contains(user);
    	
    	
    }
    
    @Test
    public void testGetSavedJobs() {
        User user = User.builder()
                .id(1L)
                .fullName("Test User")
                .email("test@test.com")
                .password("password")
                .phoneNumber("123456789")
                .savedJobs(new ArrayList<>())
                .build();

        Job job1 = Job.builder()
                .id(1L)
                .company("Test Company 1")
                .jobTitle("Title 1")
                .jobDescription("Description 1")
                .skillsRequired("Skill1, Skill2")
                .location("Location 1")
                .salary("1000")
                .build();

        Job job2 = Job.builder()
                .id(2L)
                .company("Test Company 2")
                .jobTitle("Title 2")
                .jobDescription("Description 2")
                .skillsRequired("Skill3, Skill4")
                .location("Location 2")
                .salary("2000")
                .build();

        List<Job> savedJobs = new ArrayList<>();
        savedJobs.add(job1);
        savedJobs.add(job2);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(jobRepository.findByApplicants(user)).thenReturn(savedJobs);

        List<JobDTO> savedJobDTOs = jobService.getSavedJobs(1L);

        Assertions.assertThat(savedJobDTOs).hasSize(2);
        Assertions.assertThat(savedJobDTOs.get(0).getId()).isEqualTo(job1.getId());
        Assertions.assertThat(savedJobDTOs.get(1).getId()).isEqualTo(job2.getId());
}
}
