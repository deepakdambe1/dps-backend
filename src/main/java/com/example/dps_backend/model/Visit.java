package com.example.dps_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "visit_time")
    private LocalDateTime visitTime;

    @Column(name = "symptoms", columnDefinition = "LONGTEXT")
    private String symptoms;

    @Column(name = "doc_observation", columnDefinition = "LONGTEXT")
    private String docObservation;

    @Column(name = "prescription", columnDefinition = "LONGTEXT")
    private String prescription;

    @Column(name = "treatment", columnDefinition = "LONGTEXT")
    private String treatment;

    @Column(name = "reports", columnDefinition = "LONGTEXT")
    private String reports;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "bp", length = 20)
    private String bp;

    @Column(name = "suger")
    private Double suger;

    @Column(name = "doctor_fees")
    private Double doctorFees;

    @Column(name = "upload_report")
    private String uploadReport;

}

