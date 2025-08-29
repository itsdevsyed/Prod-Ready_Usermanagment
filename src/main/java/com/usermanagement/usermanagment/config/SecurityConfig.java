package com.usermanagement.usermanagment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
            .csrf(csrf -> csrf.disable()) // disable CSRF for Postman/API testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/**").permitAll() // allow register/login
                .requestMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated() // other endpoints need auth
            )
            .httpBasic(httpBasic -> httpBasic.disable()) // disable HTTP Basic
            .formLogin(form -> form.disable()) // disable form login
            .logout(logout -> logout.disable()); // disable logout

        return http.build();
    }
}
