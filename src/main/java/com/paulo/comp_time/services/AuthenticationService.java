package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.User;
import com.paulo.comp_time.dtos.requests.UserLoginRequest;
import com.paulo.comp_time.dtos.requests.UserRegisterRequest;
import com.paulo.comp_time.dtos.responses.LoginResponse;
import com.paulo.comp_time.dtos.responses.UserRegisterResponse;
import com.paulo.comp_time.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public LoginResponse login(UserLoginRequest request) {

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authentication = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) authentication.getPrincipal();

        String token = tokenService.generateToken(user);

        return new LoginResponse(token);
    }

    public UserRegisterResponse register(UserRegisterRequest request) {
        if(userRepository.findByEmail(request.email()) != null) {
            throw new RuntimeException("The user's email is already registered");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());

        User user = new User(
                request.name(),
                request.email(),
                encryptedPassword,
                request.role()
        );

        userRepository.save(user);

        return new UserRegisterResponse(user.getName(), user.getEmail());
    }
}
