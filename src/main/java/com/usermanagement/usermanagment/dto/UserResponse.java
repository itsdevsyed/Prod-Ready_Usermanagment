package com.usermanagement.usermanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String role;

}
