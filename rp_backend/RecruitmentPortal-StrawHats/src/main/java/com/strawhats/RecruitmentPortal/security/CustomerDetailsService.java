package com.strawhats.RecruitmentPortal.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.strawhats.RecruitmentPortal.model.User;
import com.strawhats.RecruitmentPortal.repo.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public CustomerDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user =  Optional.ofNullable(userRepository.
        		findByEmail(email).
        		orElseThrow(()->new UsernameNotFoundException("User not found")));
//        return User.builder()
//                .username(user.getUserName())
//                .password(user.getPassword())
//                .roles("USER")
//                .build();

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(),new ArrayList<>());
    }
}
