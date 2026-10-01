package com.paulo.comp_time.dtos.requests;

import com.paulo.comp_time.domain.enums.UserRole;

public record UserRegisterRequest(
    String name,
    String email,
    String password,
    UserRole role
) { }
