package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.JobPosition;
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
}
