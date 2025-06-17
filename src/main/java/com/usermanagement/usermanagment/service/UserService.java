package com.usermanagement.usermanagment.service;

import java.beans.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usermanagement.usermanagment.dto.RegisterUserRequest;
import com.usermanagement.usermanagment.entity.User;
import com.usermanagement.usermanagment.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public User registerUser(RegisterUserRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        if(userRepository.existsByUsername(request.getUsername())){
            throw new IllegalArgumentException("Username already exists");
        }
        //return null;

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName((request.getFullName()));
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setEmailVerified(false);

        return userRepository.save(user);

    }

    public User  login(String email, String rawPassword){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("invalid Credentials"));

        if(!passwordEncoder.matches(rawPassword, user.getPassword())){
            new IllegalArgumentException("Invalid credential");
        }

        return user;
    }


}
