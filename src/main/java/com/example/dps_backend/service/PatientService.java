package com.example.dps_backend.service;

import com.example.dps_backend.model.Patient;
import com.example.dps_backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            Patient existingPatient = patient.get();
            existingPatient.setUser(patientDetails.getUser());
            existingPatient.setHeight(patientDetails.getHeight());
            existingPatient.setBloodGroup(patientDetails.getBloodGroup());
            existingPatient.setMaritalStatus(patientDetails.getMaritalStatus());
            existingPatient.setMedicalHistory(patientDetails.getMedicalHistory());
            return patientRepository.save(existingPatient);
        }
        return null;
    }

    public boolean deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

