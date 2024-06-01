package com.strawhats.RecruitmentPortal.service;

import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.repo.UserRepository;

public class UserService {
	UserRepository userRepository;
	
	public User registerUser(User user) {
		return userRepository.save(user);
	}

}
