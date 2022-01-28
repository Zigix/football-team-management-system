package com.example.footballteammanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerTeamView {
    private String fullName;
    private Integer number;
    private boolean reserve;
}
