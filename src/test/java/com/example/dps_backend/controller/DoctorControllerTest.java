package com.example.dps_backend.controller;

import com.example.dps_backend.model.Doctor;
import com.example.dps_backend.service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DoctorService doctorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllDoctors() throws Exception {
        Doctor doctor1 = new Doctor();
        doctor1.setId(1L);
        doctor1.setFirstName("John");
        doctor1.setLastName("Doe");
        doctor1.setSpecialization("Cardiology");

        Doctor doctor2 = new Doctor();
        doctor2.setId(2L);
        doctor2.setFirstName("Jane");
        doctor2.setLastName("Smith");
        doctor2.setSpecialization("Pediatrics");

        when(doctorService.getAllDoctors()).thenReturn(Arrays.asList(doctor1, doctor2));

        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void getDoctorById() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setFirstName("John");
        doctor.setLastName("Doe");
        doctor.setSpecialization("Cardiology");

        when(doctorService.getDoctorById(1L)).thenReturn(Optional.of(doctor));

        mockMvc.perform(get("/api/doctors/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void createDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Doe");
        doctor.setSpecialization("Cardiology");

        Doctor savedDoctor = new Doctor();
        savedDoctor.setId(1L);
        savedDoctor.setFirstName("John");
        savedDoctor.setLastName("Doe");
        savedDoctor.setSpecialization("Cardiology");

        when(doctorService.createDoctor(any(Doctor.class))).thenReturn(savedDoctor);

        mockMvc.perform(post("/api/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void updateDoctor() throws Exception {
        Doctor existingDoctor = new Doctor();
        existingDoctor.setId(1L);
        existingDoctor.setFirstName("John");
        existingDoctor.setLastName("Doe");
        existingDoctor.setSpecialization("Cardiology");

        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setId(1L);
        updatedDoctor.setFirstName("Johnny");
        updatedDoctor.setLastName("Depp");
        updatedDoctor.setSpecialization("Neurology");

        when(doctorService.getDoctorById(1L)).thenReturn(Optional.of(existingDoctor));
        when(doctorService.updateDoctor(any(Long.class), any(Doctor.class))).thenReturn(updatedDoctor);

        mockMvc.perform(put("/api/doctors/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDoctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Johnny"))
                .andExpect(jsonPath("$.lastName").value("Depp"))
                .andExpect(jsonPath("$.specialization").value("Neurology"));
    }

    @Test
    void deleteDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        when(doctorService.getDoctorById(1L)).thenReturn(Optional.of(doctor));

        mockMvc.perform(delete("/api/doctors/{id}", 1L))
                .andExpect(status().isNotFound());
    }
}