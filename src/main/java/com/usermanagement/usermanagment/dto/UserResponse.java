package com.usermanagement.usermanagment.dto;

import com.usermanagement.usermanagment.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserResponse {
    private String message = "User details fetched successfully";
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private Boolean enabled;
    private Boolean emailVerified;
    private Boolean phoneVerified;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.enabled = user.getEnabled();
        this.emailVerified = user.getEmailVerified();
        this.phoneVerified = user.getPhone() != null && !user.getPhone().isEmpty();

        // Default constructor
    }

}
