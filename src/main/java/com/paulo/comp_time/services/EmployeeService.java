package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.Employee;
import com.paulo.comp_time.exceptions.EmployeeAlreadyExistsException;
import com.paulo.comp_time.exceptions.EmployeeNotFoundException;
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

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    public Employee create(Employee employee) {
        if (!employeeRepository.findByCpf(employee.getCpf()).isEmpty()) {
            throw  new EmployeeAlreadyExistsException("The employee's CPF is already registered");
        }

        return employeeRepository.save(employee);
    }
}
