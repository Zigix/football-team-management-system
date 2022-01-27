package com.example.footballteammanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    private String email;
    private String username;
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String teamName;
}
