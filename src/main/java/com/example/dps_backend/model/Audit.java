package com.example.dps_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "action_type", length = 50)
    private String actionType;

    @Column(name = "exception_details", columnDefinition = "LONGTEXT")
    private String exceptionDetails;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by", length = 25)
    private String createdBy;

    @Column(name = "status", length = 20)
    private String status;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}

