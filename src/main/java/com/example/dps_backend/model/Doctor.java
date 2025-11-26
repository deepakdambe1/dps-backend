package com.example.dps_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

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
    private Long doctorID;
    private String clinicOpenTime;
    private String clinicCloseTime;
    private String createdBy;
    private String createdAt;
    private Integer apmmntDuration;

}