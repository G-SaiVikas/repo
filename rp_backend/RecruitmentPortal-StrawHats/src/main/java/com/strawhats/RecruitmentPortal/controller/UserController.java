package com.strawhats.RecruitmentPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.service.UserService;
import com.strawhats.RecruitmentPortal.service.EmailAlreadyExistsException;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		
	
		try {
	        User registeredUser = userService.registerUser(user);
	        return ResponseEntity.ok(registeredUser);
	    } catch (EmailAlreadyExistsException e) {
	        return ResponseEntity.status(409).body(e.getMessage());
	    }
	
	}
	
	@PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}
