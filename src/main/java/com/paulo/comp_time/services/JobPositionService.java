package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.JobPosition;
import com.paulo.comp_time.dtos.requests.JobPositionRequest;
import com.paulo.comp_time.exceptions.JobPositionAlreadyExists;
import com.paulo.comp_time.exceptions.JobPositionNotFoundException;
import com.paulo.comp_time.repositories.JobPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPositionService {

    private final JobPositionRepository jobPositionRepository;

    public List<JobPosition> getAll(boolean includeInactive) {

        if(includeInactive) {
            return jobPositionRepository.findAll();
        }

        return jobPositionRepository.findAllByActiveTrue();
    }

    public JobPosition getById(String id) {
        return jobPositionRepository.findById(id)
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found"));
    }

    public JobPosition create(JobPositionRequest request) {
        if (!jobPositionRepository.findByName(request.name()).isEmpty()) {
            throw new JobPositionAlreadyExists("Job position already exists");
        }

        JobPosition jobPosition = new JobPosition(
                request.name()
        );

        return jobPositionRepository.save(jobPosition);
    }
}
