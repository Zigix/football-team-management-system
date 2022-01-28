package com.example.footballteammanagementsystem.domain.dto;

import com.example.footballteammanagementsystem.domain.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerView {
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate dateOfBirth;
    private Integer number;
    private Position position;
    private String teamName;
    private Long teamId;
    private LocalDate joinDate;
    private boolean reserve;
}
