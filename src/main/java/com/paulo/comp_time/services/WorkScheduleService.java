package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.WorkSchedule;
import com.paulo.comp_time.dtos.requests.WorkScheduleRequest;
import com.paulo.comp_time.dtos.responses.WorkScheduleResponse;
import com.paulo.comp_time.exceptions.WorkScheduleAlreadyExists;
import com.paulo.comp_time.exceptions.WorkScheduleNotFoundException;
import com.paulo.comp_time.mappers.WorkScheduleMapper;
import com.paulo.comp_time.repositories.WorkScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkScheduleService {

    private final WorkScheduleRepository workScheduleRepository;
    private final WorkScheduleMapper workScheduleMapper;

    public List<WorkScheduleResponse> getAll(boolean includeInactive) {
        List<WorkSchedule> workSchedules = includeInactive
                ?  workScheduleRepository.findAll()
                :  workScheduleRepository.findAllByActiveTrue();

        return workSchedules.stream()
                .map(workSchedule -> workScheduleMapper.toResponse(workSchedule))
                .toList();
    }

    public WorkScheduleResponse getById(Long id) {
        WorkSchedule workSchedule = workScheduleRepository.findById(id)
                .orElseThrow(() -> new WorkScheduleNotFoundException(("Work schedule not found")));

        return workScheduleMapper.toResponse(workSchedule);
    }

    public WorkScheduleResponse create(WorkScheduleRequest workScheduleRequest) {
        WorkSchedule workSchedule = workScheduleMapper.toEntity(workScheduleRequest);

        int expectedSeconds = Math.toIntExact(Duration.between(
                workSchedule.getStartTime(),
                workSchedule.getEndTime()).toSeconds()
                - workSchedule.getBreakSeconds());

        workSchedule.setExpectedSeconds(expectedSeconds);

        return workScheduleMapper.toResponse(
                workScheduleRepository.save(workSchedule)
        );
    }

    public WorkScheduleResponse update(Long id, WorkScheduleRequest request) {

        WorkSchedule workSchedule = workScheduleRepository.findById(id)
                .orElseThrow(() ->
                        new WorkScheduleNotFoundException("Work schedule not found")
                );

        if (workScheduleRepository.existsByNameAndIdNot(request.name(), id)) {
            throw new WorkScheduleAlreadyExists("Work schedule already exists");
        }

        workSchedule.setName(request.name());
        workSchedule.setStartTime(request.startTime());
        workSchedule.setEndTime(request.endTime());
        workSchedule.setBreakSeconds(request.breakSeconds());

        int expectedSeconds = Math.toIntExact(
                Duration.between(
                        workSchedule.getStartTime(),
                        workSchedule.getEndTime()
                ).toSeconds() - workSchedule.getBreakSeconds()
        );

        workSchedule.setExpectedSeconds(expectedSeconds);

        workScheduleRepository.save(workSchedule);

        return workScheduleMapper.toResponse(workSchedule);
    }

    public void delete(Long id) {
        WorkSchedule workSchedule = workScheduleRepository.findById(id)
                .orElseThrow(() ->
                        new WorkScheduleNotFoundException("Work schedule not found"));

        workScheduleRepository.delete(workSchedule);
    }

    public WorkScheduleResponse active(Long id) {
        WorkSchedule workSchedule = workScheduleRepository.findById(id)
                .orElseThrow(() ->
                        new WorkScheduleNotFoundException("Work schedule not found"));

        workSchedule.setActive(true);

        return workScheduleMapper.toResponse(workSchedule);
    }

    public WorkScheduleResponse deactive(Long id) {
        WorkSchedule workSchedule = workScheduleRepository.findById(id)
                .orElseThrow(() ->
                        new WorkScheduleNotFoundException("Work schedule not found"));

        workSchedule.setActive(false);

        return workScheduleMapper.toResponse(workSchedule);
    }
}
