package com.paulo.comp_time.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SectorNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleSectionNotFound(
            SectorNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(JobPositionNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleJobPositionNotFound(
            JobPositionNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(JobPositionAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleJobPositionAlreadyExists(
            JobPositionAlreadyExistsException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(WorkScheduleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleWorkScheduleNotFound(
            WorkScheduleNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(WorkScheduleAlreadyExists.class)
    public ResponseEntity<Map<String, String>> handleWorkScheduleAlreadyExists(
            WorkScheduleAlreadyExists exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmployeeNotFound(
            EmployeeNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmployeeAlreadyExists(
            EmployeeAlreadyExistsException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage()));
    }
}
