package com.usermanagement.usermanagment.repository;

import java.util.Optional;
import com.usermanagement.usermanagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByusername(String username);


    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    // boolean existsByUsername(String username);

}


