package com.paulo.comp_time.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public record WorkSheduleRequest(
        @NotBlank(message = "O nome é obrigatório.")
        String name,

        @NotNull(message = "O horário de início é obrigatório.")
        LocalTime startTime,

        @NotNull(message = "O horário de término é obrigatório.")
        LocalTime endTime,

        int breakSeconds
) {
}
