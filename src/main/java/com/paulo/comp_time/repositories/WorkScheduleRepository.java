package com.paulo.comp_time.repositories;

import com.paulo.comp_time.domain.entities.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, String> {
    List<WorkSchedule> findAllByActiveTrue();
    boolean existsByNameAndIdNot(String name, String id);
}
