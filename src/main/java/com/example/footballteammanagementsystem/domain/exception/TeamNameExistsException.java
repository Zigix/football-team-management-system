package com.example.footballteammanagementsystem.domain.exception;

public class TeamNameExistsException extends RuntimeException {
    public TeamNameExistsException(String message) {
        super(message);
    }
}
