package com.usermanagement.usermanagment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Use lambda for csrf
                .authorizeHttpRequests(auth -> auth // using the lamba function for the authentication and authorization term soo that it could work like event callback
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()  // allow debugging with the actuator for logging metric , error and other stuff to monitor and catch the error
                        .requestMatchers("/api/users/**").permitAll()
                        .anyRequest().authenticated() // Add this line to secure other endpoints
                )
                .httpBasic(withDefaults()); // Use lambda for httpBasic

        return http.build();
    }
}
