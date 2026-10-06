package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.Employee;
import com.paulo.comp_time.dtos.requests.EmployeeRequest;
import com.paulo.comp_time.dtos.responses.EmployeeResponse;
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

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll(
            @RequestParam(defaultValue = "false") boolean includeInactive) {

        return ResponseEntity.ok(employeeService.getAll(includeInactive));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(
            @RequestBody @Valid EmployeeRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid EmployeeRequest request) {

        return ResponseEntity.ok(employeeService.update(id, request));
    }

}
