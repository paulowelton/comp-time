package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.Employee;
import com.paulo.comp_time.domain.entities.JobPosition;
import com.paulo.comp_time.domain.entities.Sector;
import com.paulo.comp_time.dtos.requests.EmployeeRequest;
import com.paulo.comp_time.dtos.responses.EmployeeResponse;
import com.paulo.comp_time.exceptions.EmployeeAlreadyExistsException;
import com.paulo.comp_time.exceptions.EmployeeNotFoundException;
import com.paulo.comp_time.exceptions.JobPositionNotFoundException;
import com.paulo.comp_time.exceptions.SectorNotFoundException;
import com.paulo.comp_time.mappers.EmployeeMapper;
import com.paulo.comp_time.repositories.EmployeeRepository;
import com.paulo.comp_time.repositories.JobPositionRepository;
import com.paulo.comp_time.repositories.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SectorRepository sectorRepository;
    private final JobPositionRepository jobPositionRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeResponse> getAll(boolean includeInactive) {
        List<Employee> employees = includeInactive
                ? employeeRepository.findAll()
                : employeeRepository.findAllByActiveTrue();

        List<EmployeeResponse> employeeResponses = employees
                .stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .toList();

        return employeeResponses;
    }

    public EmployeeResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        return employeeMapper.toResponse(employee);
    }

    public EmployeeResponse create(EmployeeRequest request) {
        if (!employeeRepository.findByCpf(request.cpf()).isEmpty()) {
            throw  new EmployeeAlreadyExistsException("The employee's CPF is already registered");
        }

        Sector sector = sectorRepository.findById(request.sectorId())
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        JobPosition jobPosition = jobPositionRepository.findById(request.jobPositionId())
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found"));

        Employee employee = employeeMapper.toEntity(request, sector, jobPosition);

        employeeRepository.save(employee);

        return employeeMapper.toResponse(employee);
    }

    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        employeeRepository.findByCpf(request.cpf())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw  new EmployeeAlreadyExistsException("The employee's CPF is already registered");
                    }
                });

        Sector sector = sectorRepository.findById(request.sectorId())
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        JobPosition jobPosition = jobPositionRepository.findById(request.jobPositionId())
                .orElseThrow(() -> new JobPositionNotFoundException("Job position not found"));

        employee.setName(request.name());
        employee.setCpf(request.cpf());
        employee.setSectorId(sector);
        employee.setJobPositionId(jobPosition);

        employeeRepository.save(employee);

        return employeeMapper.toResponse(employee);
    }

    public void delete(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
    }

    public EmployeeResponse activate(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        employee.setActive(true);

        return employeeMapper.toResponse(employee);
    }

    public EmployeeResponse deactivate(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        employee.setActive(false);

        return employeeMapper.toResponse(employee);
    }
}
