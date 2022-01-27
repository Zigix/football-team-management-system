package com.example.footballteammanagementsystem.api;

import com.example.footballteammanagementsystem.domain.dto.RegisterUserRequest;
import com.example.footballteammanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody RegisterUserRequest request) {
        authService.signUp(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
