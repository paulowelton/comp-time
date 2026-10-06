package com.paulo.comp_time.dtos.responses;

public record EmployeeResponse(
    Long id,
    String name,
    String cpf,
    int sectorId,
    int jobPositionId,
    boolean active
) { }
