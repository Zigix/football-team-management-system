package com.example.footballteammanagementsystem.domain.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message) {
        super(message);
    }
}
