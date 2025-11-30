package com.example.dps_backend.controller;

import com.example.dps_backend.model.Appointment;
import com.example.dps_backend.model.Doctor;
import com.example.dps_backend.model.Patient;
import com.example.dps_backend.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllAppointments() throws Exception {
        Appointment appointment1 = createTestAppointment(1L, "2024-01-15T10:00:00", "Headache", "Scheduled");
        Appointment appointment2 = createTestAppointment(2L, "2024-01-16T14:00:00", "Fever", "Scheduled");

        when(appointmentService.getAllAppointments()).thenReturn(Arrays.asList(appointment1, appointment2));

        mockMvc.perform(get("/api/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    void getAppointmentById_WhenFound() throws Exception {
        Appointment appointment = createTestAppointment(1L, "2024-01-15T10:00:00", "Headache", "Scheduled");

        when(appointmentService.getAppointmentById(1L)).thenReturn(Optional.of(appointment));

        mockMvc.perform(get("/api/appointments/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.symptoms").value("Headache"))
                .andExpect(jsonPath("$.status").value("Scheduled"));
    }

    @Test
    void getAppointmentById_WhenNotFound() throws Exception {
        when(appointmentService.getAppointmentById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/appointments/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void createAppointment() throws Exception {
        Appointment appointment = createTestAppointment(null, "2024-01-15T10:00:00", "Headache", "Scheduled");
        
        Appointment savedAppointment = createTestAppointment(1L, "2024-01-15T10:00:00", "Headache", "Scheduled");

        when(appointmentService.createAppointment(any(Appointment.class))).thenReturn(savedAppointment);

        mockMvc.perform(post("/api/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.symptoms").value("Headache"))
                .andExpect(jsonPath("$.status").value("Scheduled"));
    }

    @Test
    void updateAppointment_WhenFound() throws Exception {
        Appointment updatedAppointment = createTestAppointment(1L, "2024-01-15T11:00:00", "Severe headache", "Confirmed");

        when(appointmentService.updateAppointment(any(Long.class), any(Appointment.class)))
                .thenReturn(updatedAppointment);

        mockMvc.perform(put("/api/appointments/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAppointment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.symptoms").value("Severe headache"))
                .andExpect(jsonPath("$.status").value("Confirmed"));
    }

    @Test
    void updateAppointment_WhenNotFound() throws Exception {
        Appointment appointmentDetails = createTestAppointment(999L, "2024-01-15T10:00:00", "Headache", "Scheduled");

        when(appointmentService.updateAppointment(any(Long.class), any(Appointment.class)))
                .thenReturn(null);

        mockMvc.perform(put("/api/appointments/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointmentDetails)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAppointment_WhenFound() throws Exception {
        when(appointmentService.deleteAppointment(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/appointments/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAppointment_WhenNotFound() throws Exception {
        when(appointmentService.deleteAppointment(999L)).thenReturn(false);

        mockMvc.perform(delete("/api/appointments/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    private Appointment createTestAppointment(Long id, String appointTime, String symptoms, String status) {
        Appointment appointment = new Appointment();
        appointment.setId(id);
        appointment.setAppointTime(LocalDateTime.parse(appointTime));
        appointment.setSymptoms(symptoms);
        appointment.setStatus(status);
        appointment.setDocMobNo("1234567890");
        appointment.setCreatedBy("admin");
        appointment.setCreatedAt(LocalDateTime.now());

        // Create mock Doctor and Patient objects
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        appointment.setDoctor(doctor);

        Patient patient = new Patient();
        patient.setId(1L);
        appointment.setPatient(patient);

        return appointment;
    }
}

