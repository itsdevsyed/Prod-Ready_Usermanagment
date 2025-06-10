package com.usermanagement.usermanagment.service;

import java.util.List;

import com.usermanagement.usermanagment.dto.RegisterUserRequest;
import com.usermanagement.usermanagment.dto.UserResponse;

public interface UserService {
    UserResponse createUser(RegisterUserRequest rerequest);
    UserResponse getUserById(Long id);



    List<UserResponse> getAllUsers();
}
