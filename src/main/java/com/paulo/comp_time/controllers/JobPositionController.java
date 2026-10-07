package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.JobPosition;
import com.paulo.comp_time.dtos.requests.JobPositionRequest;
import com.paulo.comp_time.dtos.responses.JobPositionResponse;
import com.paulo.comp_time.services.JobPositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-positions")
@RequiredArgsConstructor
public class JobPositionController {

    private final JobPositionService jobPositionService;

    @GetMapping
    public ResponseEntity<List<JobPositionResponse>> getAll(
            @RequestParam(defaultValue = "false") boolean includeInactive ) {

        return ResponseEntity.ok(jobPositionService.getAll(includeInactive));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPositionResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(jobPositionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<JobPositionResponse> create(
            @RequestBody @Valid JobPositionRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(jobPositionService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPositionResponse> update(
            @RequestBody @Valid JobPositionRequest request,
            @PathVariable Long id) {

        return ResponseEntity.ok(jobPositionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        jobPositionService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<JobPositionResponse> deactivate(
            @PathVariable Long id) {

        return ResponseEntity.ok(jobPositionService.deactivate(id));
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<JobPositionResponse> activate(
            @PathVariable Long id) {

        return ResponseEntity.ok(jobPositionService.activate(id));
    }
}
