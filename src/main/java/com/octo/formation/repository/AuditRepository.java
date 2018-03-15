package com.octo.formation.repository;

import com.octo.formation.domain.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, Long> {
}
