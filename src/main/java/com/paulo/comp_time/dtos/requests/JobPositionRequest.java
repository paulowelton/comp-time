package com.paulo.comp_time.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record JobPositionRequest(
        @NotBlank(message = "name is required")
        String name
) {
}
