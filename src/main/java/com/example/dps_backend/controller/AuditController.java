package com.example.dps_backend.controller;

import com.example.dps_backend.model.Audit;
import com.example.dps_backend.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/audits")
@CrossOrigin(origins = "*")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping
    public List<Audit> getAllAudits() {
        return auditService.getAllAudits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Audit> getAuditById(@PathVariable Long id) {
        Optional<Audit> audit = auditService.getAuditById(id);
        return audit.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Audit createAudit(@RequestBody Audit audit) {
        return auditService.createAudit(audit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Audit> updateAudit(@PathVariable Long id, @RequestBody Audit auditDetails) {
        Audit updatedAudit = auditService.updateAudit(id, auditDetails);
        return updatedAudit != null
                ? ResponseEntity.ok(updatedAudit)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAudit(@PathVariable Long id) {
        return auditService.deleteAudit(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}

