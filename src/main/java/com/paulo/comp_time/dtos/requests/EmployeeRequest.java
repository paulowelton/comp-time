package com.paulo.comp_time.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "CPF is required")
        @Size(max = 11, min = 11, message = "CPF must have exactly 11 numeric characters")
        String cpf,

        @NotNull(message = "Sector ID is required")
        Integer sectorId,

        @NotNull(message = "Job position ID is required")
        Integer jobPositionId
) { }
