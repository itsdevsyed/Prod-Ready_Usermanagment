package com.usermanagement.usermanagment.entity;

import java.time.Instant;
import java.util.Objects;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenerationTime;

@Entity
@Getter
@Setter
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email" ),
    @UniqueConstraint(columnNames = "username")
}
, indexes = {
    @Index(name = "idx_username", columnList = "username"),
    @Index(name = "idx_email", columnList = "email")
})
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String username;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 15)
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid phone number format")
    private String phone;

    @Column(nullable = false)
    private Boolean  enabled = false;


    @Column(name = "email_verified",nullable = false)
    private Boolean emailVerified = false;

    @Column(name = "phone_verified", nullable = false)
    private Boolean phoneVerified = false;


    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;


    @Column(name = "update_at")
    private Instant updatedAt;

    @Column(nullable = false)
    private Boolean isDeleted = false;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.INACTIVE;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdated() {
        this.updatedAt = Instant.now();
    }


    @Builder
    public User(String username, String fullName, String email, String password, String phone){
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
    public enum UserStatus {
        ACTIVE, INACTIVE, BANNED
    }



    @Override
    public boolean equals(Object o ) {
        if (this == o) return true;
        if(!(o instanceof User)) return false;
        User user = (User)o;
        return id != null && id.equals(user.id);
    }

    @Override
    public  int hashCode(){
          return Objects.hash(id);

    }

        @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", enabled=" + enabled +
                ", emailVerified=" + emailVerified +
                ", phoneVerified=" + phoneVerified +
                ", createdAt=" + createdAt +
                ", updateAt=" + updatedAt +
                '}';
    }
    public User() {

        // Default constructor for JPA

    }



}
