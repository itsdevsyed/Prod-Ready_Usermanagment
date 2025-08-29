package com.usermanagement.usermanagment.controller;

import com.usermanagement.usermanagment.dto.LoginRequest;
import com.usermanagement.usermanagment.dto.LoginResponse;
import com.usermanagement.usermanagment.dto.RegisterUserRequest;
import com.usermanagement.usermanagment.dto.UserResponse;
import com.usermanagement.usermanagment.entity.User;
import com.usermanagement.usermanagment.exception.AccountNotActiveException;
import com.usermanagement.usermanagment.exception.EmailAlreadyExistsException;
import com.usermanagement.usermanagment.exception.InvalidCredentialsException;
import com.usermanagement.usermanagment.exception.UsernameAlreadyExistsException;
import com.usermanagement.usermanagment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        try {
            User user = userService.registerUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(user));
        } catch (EmailAlreadyExistsException | UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new UserResponse(null)); // or include message from e.getMessage()
        }
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            User user = userService.login(request.getEmail(), request.getPassword());

            return ResponseEntity.ok(new LoginResponse(
                    user.getUsername(),
                    user.getEmail(),
                    user.getFullName()
            ));
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (AccountNotActiveException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
