package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.JobPosition;
import com.paulo.comp_time.dtos.responses.JobPositionResponse;
import com.paulo.comp_time.services.JobPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
