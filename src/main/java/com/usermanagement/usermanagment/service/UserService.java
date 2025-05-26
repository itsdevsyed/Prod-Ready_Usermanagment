package com.usermanagement.usermanagment.service;

import java.util.List;

import com.usermanagement.usermanagment.dto.UserRequest;
import com.usermanagement.usermanagment.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest rerequest);
    UserResponse getUserById(Long id);

    

    List<UserResponse> getAllUsers();
}
