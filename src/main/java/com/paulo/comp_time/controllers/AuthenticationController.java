package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.User;
import com.paulo.comp_time.dtos.requests.UserLoginRequest;
import com.paulo.comp_time.dtos.requests.UserRegisterRequest;
import com.paulo.comp_time.dtos.responses.LoginResponse;
import com.paulo.comp_time.dtos.responses.UserRegisterResponse;
import com.paulo.comp_time.repositories.UserRepository;
import com.paulo.comp_time.services.TokenService;
import jakarta.validation.Valid;
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
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid UserLoginRequest request) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authentication = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) authentication.getPrincipal();

        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody @Valid UserRegisterRequest request) {
        if(this.userRepository.findByEmail(request.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());

        User user = new User(
                request.name(),
                request.email(),
                encryptedPassword,
                request.role()
        );

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserRegisterResponse(user.getName(), user.getEmail()));
    }
}
