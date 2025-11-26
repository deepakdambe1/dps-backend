package com.example.dps_backend.service;

import com.example.dps_backend.model.Doctor;
import com.example.dps_backend.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor doctor1;
    private Doctor doctor2;

    @BeforeEach
    void setUp() {
        doctor1 = new Doctor();
        doctor1.setSpecialization("Cardiology");
        doctor1.setPhone("123-456-7890");
        doctor1.setEmail("john.doe@example.com");
        doctor1.setId(1L);
        
        doctor2 = new Doctor();
        doctor2.setSpecialization("Pediatrics");
        doctor2.setPhone("098-765-4321");
        doctor2.setEmail("jane.smith@example.com");
        doctor2.setId(2L);
    }

    @Test
    void getAllDoctors() {
        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));

        List<Doctor> doctors = doctorService.getAllDoctors();

        assertNotNull(doctors);
        assertEquals(2, doctors.size());
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    void getDoctorById() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor1));

        Optional<Doctor> foundDoctor = doctorService.getDoctorById(1L);

        assertTrue(foundDoctor.isPresent());
        verify(doctorRepository, times(1)).findById(1L);
    }

    @Test
    void getDoctorById_NotFound() {
        when(doctorRepository.findById(3L)).thenReturn(Optional.empty());

        Optional<Doctor> foundDoctor = doctorService.getDoctorById(3L);

        assertFalse(foundDoctor.isPresent());
        verify(doctorRepository, times(1)).findById(3L);
    }

    @Test
    void createDoctor() {
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor1);

        Doctor createdDoctor = doctorService.createDoctor(doctor1);

        assertNotNull(createdDoctor);
        verify(doctorRepository, times(1)).save(doctor1);
    }

    @Test
    void updateDoctor() {
        Doctor updatedDetails = new Doctor();
        updatedDetails.setSpecialization("Oncology");
        updatedDetails.setPhone("123-456-7890");
        updatedDetails.setEmail("john.doe@example.com");
        updatedDetails.setId(1L);

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor1));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(updatedDetails);

        Optional<Doctor> result = Optional.ofNullable(doctorService.updateDoctor(1L, updatedDetails));

        assertTrue(result.isPresent());
        assertEquals("Oncology", result.get().getSpecialization());
        verify(doctorRepository, times(1)).findById(1L);
        verify(doctorRepository, times(1)).save(doctor1);
    }

    @Test
    void updateDoctor_NotFound() {
        Doctor updatedDetails = new Doctor();
        updatedDetails.setSpecialization("Oncology");
        updatedDetails.setPhone("123-456-7890");
        updatedDetails.setEmail("john.doe@example.com");
        updatedDetails.setId(3L);

        when(doctorRepository.findById(3L)).thenReturn(Optional.empty());

        Optional<Doctor> result = Optional.ofNullable(doctorService.updateDoctor(3L, updatedDetails));

        assertFalse(result.isPresent());
        verify(doctorRepository, times(1)).findById(3L);
        verify(doctorRepository, never()).save(any(Doctor.class));
    }

    @Test
    void deleteDoctor() {
        when(doctorRepository.existsById(1L)).thenReturn(true);
        doNothing().when(doctorRepository).deleteById(1L);

        boolean isDeleted = doctorService.deleteDoctor(1L);

        assertTrue(isDeleted);
        verify(doctorRepository, times(1)).existsById(1L);
        verify(doctorRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteDoctor_NotFound() {
        when(doctorRepository.existsById(3L)).thenReturn(false);

        boolean isDeleted = doctorService.deleteDoctor(3L);

        assertFalse(isDeleted);
        verify(doctorRepository, times(1)).existsById(3L);
        verify(doctorRepository, never()).deleteById(anyLong());
    }
}