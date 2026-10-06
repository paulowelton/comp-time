package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.Employee;
import com.paulo.comp_time.dtos.responses.EmployeeResponse;
import com.paulo.comp_time.mappers.EmployeeMapper;
import com.paulo.comp_time.services.EmployeeService;
import lombok.RequiredArgsConstructor;
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
    private ResponseEntity<List<EmployeeResponse>> getAll(
            @RequestParam(defaultValue = "false") boolean includeInactive) {

        List<EmployeeResponse> employees = employeeService.getAll(includeInactive)
                .stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .toList();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    private ResponseEntity<EmployeeResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employeeMapper.toResponse(
                        employeeService.getById(id)
                )
        );
    }



}
