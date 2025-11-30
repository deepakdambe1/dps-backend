package com.example.dps_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 12, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 12, nullable = false)
    private String password;

    @Column(name = "role", length = 10, nullable = false)
    private String role;

    @Column(name = "firstName", length = 25)
    private String firstName;

    @Column(name = "lastName", length = 25)
    private String lastName;

    @Column(name = "mobileNo", length = 12)
    private String mobileNo;

    @Column(name = "aadharCard", length = 12)
    private String aadharCard;

    @Column(name = "city", length = 25)
    private String city;

    @Column(name = "area", length = 25)
    private String area;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "lastLoginAt")
    private LocalDateTime lastLoginAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Doctor doctor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Patient patient;

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}

