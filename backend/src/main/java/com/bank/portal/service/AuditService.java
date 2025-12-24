package com.bank.portal.service;

import com.bank.portal.model.AuditLog;
import com.bank.portal.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditService {
    
    private final AuditLogRepository auditLogRepository;
    
    @Transactional
    public void logAction(String action, String performedBy, String targetEntity, String targetId, String details) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setPerformedBy(performedBy);
        log.setTargetEntity(targetEntity);
        log.setTargetId(targetId);
        log.setDetails(details);
        auditLogRepository.save(log);
    }
    
    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAllByOrderByTimestampDesc();
    }
    
    public List<AuditLog> getLogsByUser(String performedBy) {
        return auditLogRepository.findByPerformedBy(performedBy);
    }
}
