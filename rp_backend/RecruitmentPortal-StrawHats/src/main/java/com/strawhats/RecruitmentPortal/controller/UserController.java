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
	
//	@GetMapping("/login")
//    public ResponseEntity<User> loginUser(@RequestBody User user) {
//        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
//        if (loggedInUser != null) {
//            return ResponseEntity.ok(loggedInUser);
//        } else {
//            return ResponseEntity.status(401).body(null);
//        }
//    }
	
	@GetMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
//        System.out.println("Inside Login API");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<AuthResponseDTO>(new AuthResponseDTO(token), HttpStatus.OK);
	}
	
	
	 
}
