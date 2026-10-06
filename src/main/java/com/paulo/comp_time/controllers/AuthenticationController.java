package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.User;
import com.paulo.comp_time.dtos.requests.UserLoginRequest;
import com.paulo.comp_time.dtos.requests.UserRegisterRequest;
import com.paulo.comp_time.dtos.responses.LoginResponse;
import com.paulo.comp_time.dtos.responses.UserRegisterResponse;
import com.paulo.comp_time.repositories.UserRepository;
import com.paulo.comp_time.services.AuthenticationService;
import com.paulo.comp_time.services.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid UserLoginRequest request) {

        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(
            @RequestBody @Valid UserRegisterRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationService.register(request));
    }
}
