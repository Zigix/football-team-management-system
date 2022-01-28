package com.example.footballteammanagementsystem.domain.exception;

public class PlayerNumberExistsException extends RuntimeException {
    public PlayerNumberExistsException(String message) {
        super(message);
    }
}
