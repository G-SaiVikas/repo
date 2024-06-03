package com.strawhats.RecruitmentPortal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User registerUser(User user) {
		return userRepository.save(user);
	}
	
	public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        
//        System.out.println(user + "" + email);
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
	


}
