package  com.usermanagement.usermanagment.service;


import com.usermanagement.usermanagment.dto.RegisterUserRequest;
import com.usermanagement.usermanagment.entity.User;
import com.usermanagement.usermanagment.exception.AccountNotActiveException;
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
        this.UserRepository = userRepository;
        this.PasswordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(RegisterUserRequest request){
        if(userRepository.existsByUsername((request.getUsername()))){
            throw new IllegalArgumentException("Username already exists");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = new User() ;
        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setEmail((request.getEmail()));
        user.setPassword(passwordEncoder.encode((request.getPassword())));
        user.setPhone(request.getPhone());
        user.setEnabled(false);
        user.setEmailVerified(false);
        user.setPhoneVerified(false);
        user.setIsDeleted(false);
        user.setStatus(User.UserStatus.INACTIVE);

        return UserRepository.save(user);
    }
    @Transactional
    public User login(String Email, String password) {
        User user = userRepository.findByEmail(Email).orElseThrow(
                () -> new InvalidCredentialsException("User not found with email: " + Email));
        if(!passwordEncoder.matches(rawPassword, user.getPassword())){
            throw new InvalidCredentialsException("Invalid Email or password");
        }
        if(!user.getEnabled()) {
            throw new AccountNotActiveException("User account is not enabled");
        }
        if (!user.getEmailVerified()) {
            throw new IllegalArgumentException("Email not verified");
        }

        if (!user.getPhoneVerified()) {
            throw new AccountNotActiveException("Phone number not verified");
        }

        if(user.getStatus() == User.UserStatus.INACTIVE){
            throw new AccountNotActiveException("User account is inactive");
        }
    }

}