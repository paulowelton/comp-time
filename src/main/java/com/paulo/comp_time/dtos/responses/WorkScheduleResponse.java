package com.paulo.comp_time.dtos.responses;

import java.time.LocalDate;
import java.time.LocalTime;

public record WorkScheduleResponse(
    String id,
    String name,
    LocalTime startTime,
    LocalTime endTime,
    int breakSeconds,
    int expectedSeconds,
    boolean active
) {
}
