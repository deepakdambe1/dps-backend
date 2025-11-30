package com.example.dps_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {

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

    @Column(name = "appoint_time")
    private LocalDateTime appointTime;

    @Column(name = "symptoms", columnDefinition = "LONGTEXT")
    private String symptoms;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "doc_mob_no", length = 15)
    private String docMobNo;

    @Column(name = "created_by", length = 25)
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}

