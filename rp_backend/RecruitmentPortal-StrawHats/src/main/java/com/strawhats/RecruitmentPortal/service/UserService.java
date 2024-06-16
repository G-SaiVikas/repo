package com.strawhats.RecruitmentPortal.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strawhats.RecruitmentPortal.dto.JobDTO;
import com.strawhats.RecruitmentPortal.dto.UserDTO;
import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.repo.JobRepository;
import com.strawhats.RecruitmentPortal.repo.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    JobRepository jobRepository;
    
    public User registerUser(User user) {
        return userRepository.save(user);
    }
    
    public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        
        if (user != null && user.get().getPassword().equals(password)) {
            return user.get();
        }
        return null;
    }
    
    public User verifyUser(String fullName, String phoneNumber) {
        return userRepository.findByFullNameAndPhoneNumber(fullName, phoneNumber);
    }
    
    public User updateUser(Long id, String newPassword) {
        Optional<User> retrievedUser = userRepository.findById(id);
        User user = retrievedUser.get();
        user.setPassword(newPassword);
        userRepository.save(user);
        return user;
    }

    public boolean hasUserAppliedForJob(Long userId, Long jobId) {
        User user = userRepository.findById(userId).get();
        Job job = jobRepository.findById(jobId).get();
        return user.getAppliedJobs().contains(job);
    }

    public void applyForJob(Long userId, Long jobId) {
        User user = userRepository.findById(userId).get();
        Job job = jobRepository.findById(jobId).get();
        user.getAppliedJobs().add(job);
        job.getApplicants().add(user);
        userRepository.save(user);
        jobRepository.save(job);
    }
    
    public boolean hasUserSavedJob(Long userId, Long jobId) {
        User user = userRepository.findById(userId).get();
        Job job = jobRepository.findById(jobId).get();
        return user.getSavedJobs().contains(job);
    }
    
    public void saveJob(Long userId, Long jobId) {
		User user = userRepository.findById(userId).get();
        Job job = jobRepository.findById(jobId).get();
        user.getSavedJobs().add(job);
        job.getSavedJobsApplicants().add(user);
        userRepository.save(user);
        jobRepository.save(job);
	}
    
    public User updateUserProfile(Long id, String fullName, String phoneNumber, String email, byte[] profilePic, byte[] resume, Date dateOfBirth) {
        Optional<User> retrievedUser = userRepository.findById(id);
        if (retrievedUser.isPresent()) {
            User user = retrievedUser.get();
            user.setFullName(fullName);
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);
            user.setProfilePic(profilePic);
            user.setResume(resume);
            user.setDateOfBirth(dateOfBirth);
            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }
    
    
    public UserDTO viewUserProfile(Long id) {
        Optional<User> retrievedUser = userRepository.findById(id);
        if (retrievedUser.isPresent()) {
            return convertToDTO(retrievedUser.get());
        } else {
            return null;
        }
    }
    
    public UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setResume(user.getResume());
        dto.setProfilePic(user.getProfilePic());
        dto.setDateOfBirth(user.getDateOfBirth());
        return dto;
    }
    
}
