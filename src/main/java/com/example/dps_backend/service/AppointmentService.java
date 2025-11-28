package com.example.dps_backend.service;

import com.example.dps_backend.model.Appointment;
import com.example.dps_backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            Appointment existingAppointment = appointment.get();
            existingAppointment.setDoctor(appointmentDetails.getDoctor());
            existingAppointment.setPatient(appointmentDetails.getPatient());
            existingAppointment.setAppointTime(appointmentDetails.getAppointTime());
            existingAppointment.setSymptoms(appointmentDetails.getSymptoms());
            existingAppointment.setStatus(appointmentDetails.getStatus());
            existingAppointment.setDocMobNo(appointmentDetails.getDocMobNo());
            existingAppointment.setCreatedBy(appointmentDetails.getCreatedBy());
            return appointmentRepository.save(existingAppointment);
        }
        return null;
    }

    public boolean deleteAppointment(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

