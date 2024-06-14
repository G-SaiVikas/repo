package com.strawhats.RecruitmentPortal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
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
import com.strawhats.RecruitmentPortal.dto.JobDTO;
import com.strawhats.RecruitmentPortal.dto.LoginDto;
import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.repo.JobRepository;
import com.strawhats.RecruitmentPortal.repo.UserRepository;
import com.strawhats.RecruitmentPortal.security.JWTGenerator;
import com.strawhats.RecruitmentPortal.service.UserService;
import com.strawhats.RecruitmentPortal.model.Job;
import com.strawhats.RecruitmentPortal.model.MailStructure;
import com.strawhats.RecruitmentPortal.service.MailService;
import com.strawhats.RecruitmentPortal.service.EmailAlreadyExistsException;
import com.strawhats.RecruitmentPortal.service.JobService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public JWTGenerator jwtGenerator;
	
	@Autowired
	public MailService mailService;
	
	@Autowired
	public JobService jobService;
    
    @Autowired
	public MailStructure mailStructure;

	
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
//        if(userRepository.existsByEmail(email)) {
//        	System.out.println(true);
//        } else {
//        	System.out.println(false);
//        }
        Optional<User> registeredUser = userRepository.findByEmail(email);
        return new ResponseEntity<>(new AuthResponseDTO(token, registeredUser.get().getId(),email), HttpStatus.OK);
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
    public ResponseEntity<String> applyForJob(@RequestParam Long id, @RequestParam Long user_id) {
        if (userService.hasUserAppliedForJob(user_id, id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User has already applied for this job.");
        }

        userService.applyForJob(user_id, id);
        System.out.println("Applied for job " + id + " by user " + user_id);
        return ResponseEntity.ok("Job application successful.");
    }
	
    @PostMapping("/jobs/save")
    public ResponseEntity<String> saveJob(@RequestParam Long id, @RequestParam Long user_id) {
        if (userService.hasUserSavedJob(user_id, id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User has already saved this job.");
        }
        userService.saveJob(user_id, id);
        return ResponseEntity.ok("Job saved successfully.");
    }

	
	@GetMapping("/getappliedjobs")
    public List<JobDTO> getAppliedJobs(@RequestParam Long user_id) {
		
    	return jobService.getAppliedJobs(user_id);

	}
	 
}
