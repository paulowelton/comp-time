package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.Employee;
import com.paulo.comp_time.dtos.requests.EmployeeRequest;
import com.paulo.comp_time.dtos.responses.EmployeeResponse;
import com.paulo.comp_time.mappers.EmployeeMapper;
import com.paulo.comp_time.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll(
            @RequestParam(defaultValue = "false") boolean includeInactive) {

        List<EmployeeResponse> employees = employeeService.getAll(includeInactive)
                .stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .toList();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employeeMapper.toResponse(
                        employeeService.getById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(
            @RequestBody @Valid EmployeeRequest request) {

        Employee employee = employeeService.create(employeeMapper.toEntity(request));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeMapper.toResponse(employee));
    }

}
