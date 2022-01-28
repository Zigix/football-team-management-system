package com.example.footballteammanagementsystem.repository;

import com.example.footballteammanagementsystem.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByTeamName(String name);
    boolean existsByNumber(Integer number);
}
