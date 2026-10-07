package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.JobPosition;
import com.paulo.comp_time.dtos.requests.JobPositionRequest;
import com.paulo.comp_time.dtos.responses.JobPositionResponse;
import com.paulo.comp_time.exceptions.JobPositionAlreadyExistsException;
import com.paulo.comp_time.exceptions.JobPositionNotFoundException;
import com.paulo.comp_time.repositories.JobPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPositionService {

    private final JobPositionRepository jobPositionRepository;

    public List<JobPositionResponse> getAll(boolean includeInactive) {

        List<JobPosition> jobPositions = includeInactive
                ? jobPositionRepository.findAll()
                : jobPositionRepository.findAllByActiveTrue();


        return jobPositions.stream()
                .map(jobPosition -> new JobPositionResponse(
                        jobPosition.getId(),
                        jobPosition.getName(),
                        jobPosition.getActive()
                ))
                .toList();
    }

    public JobPositionResponse getById(Long id) {
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found"));

        return new JobPositionResponse(
                jobPosition.getId(),
                jobPosition.getName(),
                jobPosition.getActive()
        );
    }

    public JobPositionResponse create(JobPositionRequest request) {
        if (!jobPositionRepository.findByName(request.name()).isEmpty()) {
            throw new JobPositionAlreadyExistsException("Job position already exists");
        }

        JobPosition jobPosition = new JobPosition(
                request.name()
        );

        jobPositionRepository.save(jobPosition);

        return new JobPositionResponse(
                jobPosition.getId(),
                jobPosition.getName(),
                jobPosition.getActive()
        );
    }

    public JobPositionResponse update(Long id, JobPositionRequest request) {
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found"));

        jobPosition.setName(request.name());

        jobPositionRepository.save(jobPosition);

        return  new JobPositionResponse(
                jobPosition.getId(),
                jobPosition.getName(),
                jobPosition.getActive()
        );
    }

    public void delete(Long id) {
        JobPosition jobPosition = jobPositionRepository.findById(id)
                        .orElseThrow(() -> new JobPositionNotFoundException("Job position not found"));

        jobPositionRepository.delete(jobPosition);
    }

    public JobPositionResponse deactivate(Long id) {
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found"));

        jobPosition.setActive(false);

        jobPositionRepository.save(jobPosition);

        return new JobPositionResponse(
                jobPosition.getId(),
                jobPosition.getName(),
                jobPosition.getActive()
        );
    }

    public JobPositionResponse activate(Long id) {
        JobPosition jobPosition = jobPositionRepository.findById(id)
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found"));

        jobPosition.setActive(true);

        jobPositionRepository.save(jobPosition);

        return new JobPositionResponse(
                jobPosition.getId(),
                jobPosition.getName(),
                jobPosition.getActive()
        );
    }

}
