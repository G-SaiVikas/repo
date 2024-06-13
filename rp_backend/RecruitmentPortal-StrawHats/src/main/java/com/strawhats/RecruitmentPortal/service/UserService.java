package com.strawhats.RecruitmentPortal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public void saveJob(Long userId, Long jobId) {
		User user = userRepository.findById(userId).get();
        Job job = jobRepository.findById(jobId).get();
        user.getSavedJobs().add(job);
        job.getSavedJobsApplicants().add(user);
        userRepository.save(user);
        jobRepository.save(job);
	}
    
}
