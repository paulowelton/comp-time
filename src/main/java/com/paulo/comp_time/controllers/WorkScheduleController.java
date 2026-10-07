package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.WorkSchedule;
import com.paulo.comp_time.dtos.requests.WorkScheduleRequest;
import com.paulo.comp_time.dtos.responses.WorkScheduleResponse;
import com.paulo.comp_time.mappers.WorkScheduleMapper;
import com.paulo.comp_time.services.WorkScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<WorkScheduleResponse> create(
            @RequestBody @Valid WorkScheduleRequest request) {

        WorkSchedule workSchedule = workScheduleService.create(workScheduleMapper.toEntity(request));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(workScheduleMapper.toResponse(workSchedule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkScheduleResponse> update(
            @PathVariable String id,
            @RequestBody @Valid WorkScheduleRequest request) {

        WorkSchedule workSchedule = workScheduleService.update(
                id,
                workScheduleMapper.toEntity(request)
        );

        return ResponseEntity.ok(
                workScheduleMapper.toResponse(workSchedule)
        );
    }
}
