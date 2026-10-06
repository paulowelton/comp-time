package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.WorkSchedule;
import com.paulo.comp_time.exceptions.WorkScheduleNotFoundException;
import com.paulo.comp_time.repositories.WorkScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkScheduleService {

    private final WorkScheduleRepository workScheduleRepository;

    public List<WorkSchedule> getAll(boolean includeInactive) {
        if (includeInactive) {
            return workScheduleRepository.findAll();
        }

        return workScheduleRepository.findAllByActiveTrue();
    }

    public WorkSchedule getById(String id) {
        return workScheduleRepository.findById(id)
                .orElseThrow(() -> new WorkScheduleNotFoundException(("Work schedule not found")));
    }

    public WorkSchedule create(WorkSchedule workSchedule) {
        int expectedSeconds = Math.toIntExact(Duration.between(
                workSchedule.getStartTime(),
                workSchedule.getEndTime()).toSeconds()
                - workSchedule.getBreakSeconds());

        workSchedule.setExpectedSeconds(expectedSeconds);

        return workSchedule;
    }



}
