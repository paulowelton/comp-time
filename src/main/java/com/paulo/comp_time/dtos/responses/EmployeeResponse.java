package com.paulo.comp_time.dtos.responses;

import com.paulo.comp_time.domain.entities.JobPosition;
import com.paulo.comp_time.domain.entities.Sector;

public record EmployeeResponse(
    Long id,
    String name,
    String cpf,
    Sector sectorId,
    JobPosition jobPositionId,
    boolean active
) { }
