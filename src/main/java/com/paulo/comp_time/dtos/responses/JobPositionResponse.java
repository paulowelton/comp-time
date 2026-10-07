package com.paulo.comp_time.dtos.responses;

public record JobPositionResponse(
        Long id,
        String name,
        Boolean active
) {
}
