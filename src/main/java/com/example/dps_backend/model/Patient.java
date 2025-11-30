package com.example.dps_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "height")
    private Double height;

    @Column(name = "blood_group", length = 5)
    private String bloodGroup;

    @Column(name = "marital_status", length = 20)
    private String maritalStatus;

    @Column(name = "medical_history", columnDefinition = "LONGTEXT")
    private String medicalHistory;

    @Column(name = "created_by", length = 25, nullable = false)
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}

