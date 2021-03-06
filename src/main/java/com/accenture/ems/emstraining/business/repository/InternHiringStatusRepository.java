package com.accenture.ems.emstraining.business.repository;

import com.accenture.ems.emstraining.business.repository.model.InternHiringStatusDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternHiringStatusRepository extends JpaRepository<InternHiringStatusDAO, Long> {
}
