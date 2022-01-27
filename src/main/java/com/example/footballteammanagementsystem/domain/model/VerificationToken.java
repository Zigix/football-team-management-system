package com.example.footballteammanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tokens")
@Getter
@Setter
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;

    @OneToOne
    private User user;
}
