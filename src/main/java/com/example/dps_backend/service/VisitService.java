package com.example.dps_backend.service;

import com.example.dps_backend.model.Visit;
import com.example.dps_backend.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Optional<Visit> getVisitById(Long id) {
        return visitRepository.findById(id);
    }

    public Visit createVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public Visit updateVisit(Long id, Visit visitDetails) {
        Optional<Visit> visit = visitRepository.findById(id);
        if (visit.isPresent()) {
            Visit existingVisit = visit.get();
            existingVisit.setDoctor(visitDetails.getDoctor());
            existingVisit.setPatient(visitDetails.getPatient());
            existingVisit.setVisitTime(visitDetails.getVisitTime());
            existingVisit.setSymptoms(visitDetails.getSymptoms());
            existingVisit.setDocObservation(visitDetails.getDocObservation());
            existingVisit.setPrescription(visitDetails.getPrescription());
            existingVisit.setTreatment(visitDetails.getTreatment());
            existingVisit.setReports(visitDetails.getReports());
            existingVisit.setWeight(visitDetails.getWeight());
            existingVisit.setBp(visitDetails.getBp());
            existingVisit.setSuger(visitDetails.getSuger());
            existingVisit.setDoctorFees(visitDetails.getDoctorFees());
            existingVisit.setUploadReport(visitDetails.getUploadReport());
            return visitRepository.save(existingVisit);
        }
        return null;
    }

    public boolean deleteVisit(Long id) {
        if (visitRepository.existsById(id)) {
            visitRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

