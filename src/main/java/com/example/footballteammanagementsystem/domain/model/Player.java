package com.example.footballteammanagementsystem.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Team team;
}
