package com.usermanagement.usermanagment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private String phone;

    @NotBlank
    private String password;


}
