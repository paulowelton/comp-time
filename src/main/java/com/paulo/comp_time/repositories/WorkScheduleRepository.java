package com.paulo.comp_time.repositories;

import com.paulo.comp_time.domain.entities.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, String> {
}
