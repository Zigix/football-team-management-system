package com.example.footballteammanagementsystem.service;

import com.example.footballteammanagementsystem.domain.dto.PlayerTeamView;
import com.example.footballteammanagementsystem.domain.dto.TeamView;
import com.example.footballteammanagementsystem.domain.exception.TeamNotFoundException;
import com.example.footballteammanagementsystem.domain.mapper.PlayerMapper;
import com.example.footballteammanagementsystem.domain.model.Team;
import com.example.footballteammanagementsystem.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;

    @Transactional(readOnly = true)
    public List<TeamView> getAll() {
        return teamRepository.findAll()
                .stream()
                .map(team -> {
                    List<PlayerTeamView> playerTeamViewList = team
                            .getPlayers()
                            .stream()
                            .map(playerMapper::toPlayerTeamView)
                            .toList();
                    TeamView teamView = new TeamView();
                    teamView.setName(teamView.getName());
                    teamView.setTrainer(team.getUser().getFirstName() + team.getUser().getLastName());
                    teamView.setPlayers(playerTeamViewList);
                    return teamView;
                }).toList();
    }

    @Transactional(readOnly = true)
    public TeamView get(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team with id " + id + " not found"));
        return new TeamView(
                team.getName(),
                team.getUser().getFirstName() + team.getUser().getLastName(),
                team.getPlayers()
                        .stream()
                        .map(playerMapper::toPlayerTeamView)
                        .toList()
        );
    }
}
