package com.example.footballteammanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate dateOfBirth;
    private Integer number;

    @Enumerated(value = EnumType.STRING)
    private Position position;
    private LocalDate joinDate;
    private boolean reserve;

    @ManyToOne
    private Team team;
}
