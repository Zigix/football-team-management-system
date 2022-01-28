package com.example.footballteammanagementsystem.domain.dto;

import com.example.footballteammanagementsystem.domain.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePlayerPositionRequest {
    private Long id;
    private Position position;
}
