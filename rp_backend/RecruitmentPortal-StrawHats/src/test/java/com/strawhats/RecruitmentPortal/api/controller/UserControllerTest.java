package com.strawhats.RecruitmentPortal.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strawhats.RecruitmentPortal.api.config.TestSecurityConfig;
import com.strawhats.RecruitmentPortal.controller.UserController;
import com.strawhats.RecruitmentPortal.dto.AuthResponseDTO;
import com.strawhats.RecruitmentPortal.model.MailStructure;
import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.repo.UserRepository;
import com.strawhats.RecruitmentPortal.service.EmailAlreadyExistsException;
import com.strawhats.RecruitmentPortal.service.MailService;
import com.strawhats.RecruitmentPortal.service.UserService;
import com.strawhats.RecruitmentPortal.security.JWTGenerator;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    
    @Mock
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTGenerator jwtGenerator;
    
    @Mock
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private MailService mailService;
    
    @MockBean
    private UserRepository userRepository;
    
    @MockBean
    private MailStructure mailStructure;

    private User user;
    
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void init() {
        this.user = User.builder()
            .fullName("Test test")
            .email("test@test.com")
            .password("test")
            .phoneNumber("123456789")
            .build();
        
        userController.authenticationManager = authenticationManager;
        userController.userRepository = userRepository;
        userController.passwordEncoder = passwordEncoder;
        userController.jwtGenerator = jwtGenerator;
        userController.mailService = mailService;
        userController.mailStructure = mailStructure;
        userController.userService = userService;
    }

    @Test
    void testRegisterUser() throws Exception {
        when(userService.registerUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isOk());
    }
    
    @Test
    public void testRegisterUser_EmailAlreadyExistsException() {
        when(userRepository.save(any(User.class))).thenThrow(new EmailAlreadyExistsException("Email already exists"));

        ResponseEntity<?> response = userController.registerUser(user);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        Assertions.assertThat(response.getBody()).isEqualTo("Email already exists");
    }

    @Test
    public void testLogin() {
    	Authentication authentication = Mockito.mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(jwtGenerator.generateToken(authentication)).thenReturn("jwtToken");
        when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.of(user));

        ResponseEntity<AuthResponseDTO> response = userController.login("test@test.com", "password");

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getAccessToken()).isEqualTo("jwtToken");
        Assertions.assertThat(response.getBody().getEmail()).isEqualTo("test@test.com");
    }

    @Test
    public void testForgotPassword() {
        when(userService.verifyUser(any(String.class), any(String.class))).thenReturn(user);

        ResponseEntity<User> response = userController.forgotPassword("Test User", "1234567890");

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(user);
    }

    @Test
    public void testForgotPassword_UserNotFound() {
        when(userService.verifyUser(any(String.class), any(String.class))).thenReturn(null);

        ResponseEntity<User> response = userController.forgotPassword("Test User", "1234567890");

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(response.getBody()).isNull();
    }

    @Test
    public void testResetPassword() {
    	when(passwordEncoder.encode(any(String.class))).thenReturn("encodedNewPassword");
        when(userService.updateUser(any(Long.class), any(String.class))).thenReturn(user);

        ResponseEntity<User> response = userController.resetPassword(1L, "newPassword");

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(user);
    }

    @Test
    public void testResetPassword_UserNotFound() {
        when(userService.updateUser(any(Long.class), any(String.class))).thenReturn(null);

        ResponseEntity<User> response = userController.resetPassword(1L, "newPassword");

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Assertions.assertThat(response.getBody()).isNull();
    }
}
