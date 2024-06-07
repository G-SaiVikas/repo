package com.strawhats.RecruitmentPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strawhats.RecruitmentPortal.dto.AuthResponseDTO;
import com.strawhats.RecruitmentPortal.dto.LoginDto;
import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.repo.UserRepository;
import com.strawhats.RecruitmentPortal.security.JWTGenerator;
import com.strawhats.RecruitmentPortal.service.UserService;
import com.strawhats.RecruitmentPortal.model.MailStructure;
import com.strawhats.RecruitmentPortal.service.MailService;
import com.strawhats.RecruitmentPortal.service.EmailAlreadyExistsException;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTGenerator jwtGenerator;
	
	@Autowired
    private MailService mailService;
    
    @Autowired
    private MailStructure mailStructure;

	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		
	
		try {
	        User registeredUser = new User();
	        registeredUser.setEmail(user.getEmail());
	        registeredUser.setFullName(user.getFullName());
	        registeredUser.setPhoneNumber(user.getPhoneNumber());
	        registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));
	        
	        userRepository.save(registeredUser);
	        
	        return ResponseEntity.ok(registeredUser);
	    } catch (EmailAlreadyExistsException e) {
	        return ResponseEntity.status(409).body(e.getMessage());
	    }
	
	}
	
	
	@GetMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestParam String email, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token, email), HttpStatus.OK);
    }
	
	@GetMapping("/forgotpassword")
    public ResponseEntity<User> forgotPassword(@RequestParam String fullName, @RequestParam String phoneNumber) {
    	System.out.println("Hello");
    	User userVerified = userService.verifyUser(fullName, phoneNumber);
    	System.out.println("hi "+ userVerified);
        if (userVerified != null) {
        	mailStructure.setSubject("Reset Password - Recruitment Portal");
        	String body = "Dear " + fullName + ",\n" +
                    	  "To reset your password, please visit \n http://localhost:3000/resetpassword";
        	mailStructure.setMessage(body);
        	mailService.sendMail(userVerified.getEmail(), mailStructure);
            return ResponseEntity.ok(userVerified);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
	
	@PutMapping("/resetpassword")
    public ResponseEntity<User> resetPassword(@RequestParam Long id, @RequestParam String newPassword) {
		
        User updatedUser = userService.updateUser(id, passwordEncoder.encode(newPassword));
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
	
	@PostMapping("/jobs/apply")
    public ResponseEntity<Void> applyForJob(@RequestParam Long id, @RequestBody User user) {
    	
      userService.applyForJob(user.getId(), id);
      System.out.println("Applied job " + id + "user" + user.getId());
      return ResponseEntity.ok().build();
    }
	
	 
}
