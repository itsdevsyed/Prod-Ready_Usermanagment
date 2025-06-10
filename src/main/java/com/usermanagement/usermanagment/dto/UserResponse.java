package com.usermanagement.usermanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private Boolean enabled;
    private Boolean emailVerified;
    private Boolean phoneVerified;

    public UserResponse() {
        // Default constructor
    }
    
}
