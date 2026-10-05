package com.paulo.comp_time.repositories;

import com.paulo.comp_time.domain.entities.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobPositionRepository extends JpaRepository<JobPosition, String> {
    List<JobPosition> findAllByActiveTrue();
    Optional<JobPosition> findByName(String name);
}
