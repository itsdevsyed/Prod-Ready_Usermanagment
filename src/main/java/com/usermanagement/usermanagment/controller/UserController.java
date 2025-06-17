package com.usermanagement.usermanagment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.usermanagment.dto.RegisterUserRequest;
import com.usermanagement.usermanagment.dto.UserResponse;
import com.usermanagement.usermanagment.entity.User;
import com.usermanagement.usermanagment.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse registerUser(@Valid @RequestBody RegisterUserRequest request) {
        User user = userService.registerUser(request);
        return new UserResponse(user);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestParam String email, @RequestParam String password) {
        User user = userService.login(email, password);
        return new UserResponse(user);

    }

}
