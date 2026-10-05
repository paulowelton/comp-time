package com.paulo.comp_time.repositories;

import com.paulo.comp_time.domain.entities.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionRepository extends JpaRepository<JobPosition, String> {
}
