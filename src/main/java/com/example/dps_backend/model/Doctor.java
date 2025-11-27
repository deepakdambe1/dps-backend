package com.example.dps_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degree;
    private String specialization;
    private String phone;
    private String email;
    private String receptionID;
    private String doctorID;
    private String clinicOpenTime;
    private String clinicCloseTime;
    private Integer apmmntDuration;
    private String createdBy;
    private LocalDateTime createdAt;

}