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

        List<JobPositionResponse> jobPositions = jobPositionService.getAll(includeInactive)
                .stream()
                .map(jobPosition -> new JobPositionResponse(
                        jobPosition.getName(),
                        jobPosition.getActive()
                ))
                .toList();

        return ResponseEntity.ok(jobPositions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPositionResponse> getById(
            @PathVariable String id) {

        JobPosition jobPosition = jobPositionService.getById(id);

        return ResponseEntity.ok(new JobPositionResponse(
                jobPosition.getName(),
                jobPosition.getActive()
        ));
    }

    @PostMapping
    public ResponseEntity<JobPositionResponse> create(
            @RequestBody @Valid JobPositionRequest request) {

        JobPosition jobPosition = jobPositionService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new JobPositionResponse(
                        jobPosition.getName(),
                        jobPosition.getActive()
                ));
    }
}
