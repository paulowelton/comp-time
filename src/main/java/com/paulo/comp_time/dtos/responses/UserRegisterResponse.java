package com.paulo.comp_time.dtos.responses;

public record UserRegisterResponse(
        Long id,
        String name,
        String email,
        Boolean active) {
}
