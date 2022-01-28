package com.example.footballteammanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingView {
    private Long id;
    private String topic;
    private String description;
    private LocalDateTime date;
    private String trainer;
}
