package com.example.dps_backend.service;

import com.example.dps_backend.model.Audit;
import com.example.dps_backend.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    public List<Audit> getAllAudits() {
        return auditRepository.findAll();
    }

    public Optional<Audit> getAuditById(Long id) {
        return auditRepository.findById(id);
    }

    public Audit createAudit(Audit audit) {
        return auditRepository.save(audit);
    }

    public Audit updateAudit(Long id, Audit auditDetails) {
        Optional<Audit> audit = auditRepository.findById(id);
        if (audit.isPresent()) {
            Audit existingAudit = audit.get();
            existingAudit.setActionType(auditDetails.getActionType());
            existingAudit.setExceptionDetails(auditDetails.getExceptionDetails());
            existingAudit.setCreatedBy(auditDetails.getCreatedBy());
            existingAudit.setStatus(auditDetails.getStatus());
            existingAudit.setDoctor(auditDetails.getDoctor());
            existingAudit.setPatient(auditDetails.getPatient());
            return auditRepository.save(existingAudit);
        }
        return null;
    }

    public boolean deleteAudit(Long id) {
        if (auditRepository.existsById(id)) {
            auditRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

