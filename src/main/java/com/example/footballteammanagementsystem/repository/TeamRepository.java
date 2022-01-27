package com.example.footballteammanagementsystem.repository;

import com.example.footballteammanagementsystem.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByUserUsername(String username);
    boolean existsByName(String name);
}
