package com.usermanagement.usermanagment.service;

import com.usermanagement.usermanagment.dto.RegisterUserRequest;
import com.usermanagement.usermanagment.entity.User;
import com.usermanagement.usermanagment.exception.AccountNotActiveException;
import com.usermanagement.usermanagment.exception.EmailAlreadyExistsException;
import com.usermanagement.usermanagment.exception.InvalidCredentialsException;
import com.usermanagement.usermanagment.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(RegisterUserRequest request) throws EmailAlreadyExistsException {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
            // or: throw new UsernameAlreadyExistsException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setEnabled(false);
        user.setEmailVerified(false);
        user.setPhoneVerified(false);
        user.setIsDeleted(false);
        user.setStatus(User.UserStatus.INACTIVE);

        return userRepository.save(user);
    }

    @Transactional
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        if (Boolean.FALSE.equals(user.getEnabled())) {
            throw new AccountNotActiveException("User account is not enabled");
        }
        if (Boolean.FALSE.equals(user.getEmailVerified())) {
            throw new AccountNotActiveException("Email not verified");
        }
        if (Boolean.FALSE.equals(user.getPhoneVerified())) {
            throw new AccountNotActiveException("Phone number not verified");
        }
        if (user.getStatus() == User.UserStatus.INACTIVE) {
            throw new AccountNotActiveException("User account is inactive");
        }

        return user; // don’t re-save unnecessarily
    }
}
