package com.example.footballteammanagementsystem.domain.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate dateOfBirth;
    private Integer number;
    private Position position;
    private LocalDate joinDate;
    private boolean reserve;

    @ManyToOne
    private Team team;
}
