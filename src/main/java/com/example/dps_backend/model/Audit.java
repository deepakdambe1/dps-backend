package com.example.dps_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actionType;
    private String exceptionDetails;
    private LocalDateTime createdAt;
    private String createdBy;
    private String status;

    @OneToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = true)
    private Patient patient;

}

