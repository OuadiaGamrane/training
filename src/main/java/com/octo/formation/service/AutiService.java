package com.octo.formation.service;

import com.octo.formation.domain.Audit;
import com.octo.formation.domain.util.EventType;
import com.octo.formation.repository.AuditRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AutiService {

  @Autowired
  private AuditRepository auditRepository;

  public void audit(EventType eventType, String message) {
    Audit audit = new Audit();
    audit.setEventType(eventType);
    audit.setMessage(message);
    auditRepository.save(audit);
  }
}
