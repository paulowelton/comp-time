package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.WorkSchedule;
import com.paulo.comp_time.dtos.requests.WorkScheduleRequest;
import com.paulo.comp_time.dtos.responses.WorkScheduleResponse;
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

    @GetMapping
    public ResponseEntity<List<WorkScheduleResponse>> getAll(
            @RequestParam(defaultValue = "false") boolean includeInactive) {

        return ResponseEntity.ok(workScheduleService.getAll(includeInactive));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkScheduleResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(workScheduleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<WorkScheduleResponse> create(
            @RequestBody @Valid WorkScheduleRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(workScheduleService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkScheduleResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid WorkScheduleRequest request) {

        return ResponseEntity.ok(workScheduleService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        workScheduleService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<WorkScheduleResponse> active(
            @PathVariable Long id) {

        return ResponseEntity.ok(workScheduleService.active(id));
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<WorkScheduleResponse> deactive(
            @PathVariable Long id) {

        return ResponseEntity.ok(workScheduleService.deactive(id));
    }
}
