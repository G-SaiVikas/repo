package com.strawhats.RecruitmentPortal.api.config;

import com.strawhats.RecruitmentPortal.model.MailStructure;
import com.strawhats.RecruitmentPortal.repo.JobRepository;
import com.strawhats.RecruitmentPortal.repo.UserRepository;
import com.strawhats.RecruitmentPortal.security.CustomerDetailsService;
import com.strawhats.RecruitmentPortal.security.JwtAuthEntryPoint;
import com.strawhats.RecruitmentPortal.service.JobService;
import com.strawhats.RecruitmentPortal.service.MailService;
import com.strawhats.RecruitmentPortal.security.JWTAuthenticationFilter;
import com.strawhats.RecruitmentPortal.security.JWTGenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;

@Configuration
public class TestSecurityConfig {

    @Bean
    @Primary
    public CustomerDetailsService customerDetailsService() {
        return mock(CustomerDetailsService.class);
    }
    
    @Bean
    @Primary
    public JWTGenerator jwtGenerator() {
        return mock(JWTGenerator.class);
    }
    
    
    @Bean
    @Primary
    public UserRepository userRepository() {
        return mock(UserRepository.class);
    }
    
    @Bean
    @Primary
    public MailService mailService() {
        return mock(MailService.class);
    }

    @Bean
    @Primary
    public JwtAuthEntryPoint authEntryPoint() {
        return mock(JwtAuthEntryPoint.class);
    }
    
    @Bean
    @Primary
    public MailStructure mailStructure() {
        return mock(MailStructure.class);
    }
    @Bean
    @Primary
    public JavaMailSender javaMailSender() {
        return mock(JavaMailSender.class);
    }

    @Bean
    @Primary
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return mock(JWTAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Primary
    public JobService testJobService() {
        return mock(JobService.class);
    }
    
    @Bean
    @Primary
    public JobRepository testJobRepository() {
        return mock(JobRepository.class);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

