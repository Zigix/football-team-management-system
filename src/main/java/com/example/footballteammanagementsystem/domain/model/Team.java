package com.example.footballteammanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Instant createdDate;

    @OneToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private List<Player> players;
}
