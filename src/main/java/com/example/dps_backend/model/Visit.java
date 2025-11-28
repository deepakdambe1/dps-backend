package com.example.dps_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private LocalDateTime visitTime;
    private String symptoms;
    private String docObservation;
    private String prescription;
    private String treatment;
    private String reports;
    private Double weight;
    private String bp;
    private Double suger;
    private Double doctorFees;
    private String uploadReport;

}

