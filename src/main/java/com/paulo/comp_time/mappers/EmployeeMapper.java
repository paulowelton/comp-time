package com.paulo.comp_time.mappers;

import com.paulo.comp_time.domain.entities.Employee;
import com.paulo.comp_time.dtos.requests.EmployeeRequest;
import com.paulo.comp_time.dtos.responses.EmployeeResponse;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {
        return new Employee(
                request.name(),
                request.cpf(),
                request.sectorId(),
                request.jobPositionId()
        );
    }

    public EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getCpf(),
                employee.getSectorId(),
                employee.getJobPositionId(),
                employee.isActive()
        );
    }
}
