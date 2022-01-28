package com.example.footballteammanagementsystem.configuration.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @EventListener(Exception.class)
    public ApiErrorCall handle(Exception e) {
        return new ApiErrorCall(e.getMessage());
    }

    @RequiredArgsConstructor
    private record ApiErrorCall(String message) {
    }
}
