package com.paulo.comp_time.repositories;

import com.paulo.comp_time.domain.entities.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, String> {
    List<WorkSchedule> findAllByActiveTrue();
}
