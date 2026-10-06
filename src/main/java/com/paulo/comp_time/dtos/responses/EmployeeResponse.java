package com.paulo.comp_time.dtos.responses;

public record EmployeeResponse(
    String id,
    String name,
    String cpf,
    int sectorId,
    int jobPositionId,
    boolean active
) { }
