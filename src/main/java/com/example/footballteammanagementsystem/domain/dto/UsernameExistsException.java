package com.example.footballteammanagementsystem.domain.dto;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String message) {
        super(message);
    }
}
