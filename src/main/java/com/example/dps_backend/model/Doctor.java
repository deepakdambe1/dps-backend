package com.example.dps_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "degree", length = 10)
    private String degree;

    @Column(name = "specialization", length = 25)
    private String specialization;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "email", length = 25)
    private String email;

    @Column(name = "reception_id", length = 100)
    private String receptionID;

    @Column(name = "doctor_id", length = 11)
    private String doctorID;

    @Column(name = "clinic_open_time")
    private String clinicOpenTime;

    @Column(name = "clinic_close_time")
    private String clinicCloseTime;

    @Column(name = "apmmnt_duration")
    private Integer apmmntDuration;

    @Column(name = "created_by", length = 25)
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}