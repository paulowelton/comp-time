package com.paulo.comp_time.controllers;

import com.paulo.comp_time.dtos.responses.WorkScheduleResponse;
import com.paulo.comp_time.mappers.WorkScheduleMapper;
import com.paulo.comp_time.services.WorkScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work-schedules")
@RequiredArgsConstructor
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;

    private final WorkScheduleMapper workScheduleMapper;

    @GetMapping
    public ResponseEntity<List<WorkScheduleResponse>> getAll(
            @RequestParam(defaultValue = "false") boolean includeInactive) {

        List<WorkScheduleResponse> workSchedules = workScheduleService.getAll(includeInactive)
                .stream()
                .map(workSchedule -> workScheduleMapper.toResponse(workSchedule))
                .toList();

        return ResponseEntity.ok(workSchedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkScheduleResponse> getById(
            @PathVariable String id) {

        return ResponseEntity.ok(
                workScheduleMapper.toResponse(
                        workScheduleService.getById(id)
                )
        );
    }


}
