package com.paulo.comp_time.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank(message = "email is required")
        @Email(message = "invalid email")
        String email,

        @NotBlank(message = "password is required")
        String password
) {}
