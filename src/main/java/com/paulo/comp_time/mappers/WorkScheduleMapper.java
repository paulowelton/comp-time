package com.paulo.comp_time.mappers;

import com.paulo.comp_time.domain.entities.WorkSchedule;
import com.paulo.comp_time.dtos.requests.WorkScheduleRequest;
import com.paulo.comp_time.dtos.responses.WorkScheduleResponse;
import org.springframework.stereotype.Component;

@Component
public class WorkScheduleMapper {
    public WorkSchedule toEntity(WorkScheduleRequest request) {
        WorkSchedule workSchedule = new WorkSchedule();

        workSchedule.setName(request.name());
        workSchedule.setStartTime(request.startTime());
        workSchedule.setEndTime(request.endTime());
        workSchedule.setBreakSeconds(request.breakSeconds());

        return workSchedule;
    }

    public WorkScheduleResponse toResponse(WorkSchedule workSchedule) {

        return new WorkScheduleResponse(
                workSchedule.getId(),
                workSchedule.getName(),
                workSchedule.getStartTime(),
                workSchedule.getEndTime(),
                workSchedule.getBreakSeconds(),
                workSchedule.getExpectedSeconds(),
                workSchedule.isActive()
        );
    }
}
