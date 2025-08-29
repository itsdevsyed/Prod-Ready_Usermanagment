package com.usermanagement.usermanagment.dto;

public class LoginResponse {
    private String username;
    private String email;
    private String fullName;
    private String token; // e.g. JWT if you add authentication later

    public LoginResponse() {
    }

    public LoginResponse(String username, String email, String fullName, String token) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
