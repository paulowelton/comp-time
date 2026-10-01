package com.paulo.comp_time.dtos.requests;

public record UserLoginRequest(
   String email,
   String password
) {}
