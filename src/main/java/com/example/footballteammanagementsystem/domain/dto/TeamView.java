package com.example.footballteammanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamView {
    private String name;
    private String trainer;
    List<PlayerTeamView> players;
}
