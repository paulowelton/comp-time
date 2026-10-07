package com.paulo.comp_time.dtos.responses;

import java.time.LocalTime;

public record WorkScheduleResponse(
    Long id,
    String name,
    LocalTime startTime,
    LocalTime endTime,
    int breakSeconds,
    int expectedSeconds,
    boolean active
) {
}
