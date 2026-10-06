package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.Employee;
import com.paulo.comp_time.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAll(boolean includeInactive) {
        if (includeInactive) {
            return employeeRepository.findAll();
        }

        return employeeRepository.findAllByActiveTrue();
    }


}
