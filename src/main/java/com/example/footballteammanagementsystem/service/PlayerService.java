package com.example.footballteammanagementsystem.service;

import com.example.footballteammanagementsystem.domain.dto.AddPlayerRequest;
import com.example.footballteammanagementsystem.domain.dto.ChangePlayerPositionRequest;
import com.example.footballteammanagementsystem.domain.dto.PlayerView;
import com.example.footballteammanagementsystem.domain.exception.PlayerNotFoundException;
import com.example.footballteammanagementsystem.domain.exception.PlayerNumberExistsException;
import com.example.footballteammanagementsystem.domain.exception.TeamNotFoundException;
import com.example.footballteammanagementsystem.domain.mapper.PlayerMapper;
import com.example.footballteammanagementsystem.domain.model.Player;
import com.example.footballteammanagementsystem.domain.model.Team;
import com.example.footballteammanagementsystem.domain.model.User;
import com.example.footballteammanagementsystem.repository.PlayerRepository;
import com.example.footballteammanagementsystem.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.swing.plaf.nimbus.State;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final AuthService authService;
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final TeamRepository teamRepository;

    @Transactional
    public void add(AddPlayerRequest request) {
        if (playerRepository.existsByNumber(request.getNumber())) {
            throw new PlayerNumberExistsException(
                    "Player with number " + request.getNumber() + " already exists in this team");
        }
        Team team = teamRepository.findByUserUsername(authService.getLoggedUser().getUsername());
        Player player = playerMapper.toPlayer(request, team);
        playerRepository.save(player);
    }

    @Transactional(readOnly = true)
    public PlayerView get(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found"));
        return playerMapper.toPlayerView(player);
    }

    @Transactional(readOnly = true)
    public List<PlayerView> getByTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team with id " + id + " not found"));
        return playerRepository.findAllByTeamName(team.getName())
                .stream()
                .map(playerMapper::toPlayerView)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found"));
        if (!player.getTeam().getUser().getUsername().equals(authService.getLoggedUser().getUsername())) {
            throw new IllegalStateException("You have no permission");
        }
        playerRepository.delete(player);
    }

    @Transactional
    public PlayerView changePosition(ChangePlayerPositionRequest request) {
        Player player = playerRepository.findById(request.getId())
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + request.getId() + " not found"));
        if (!player.getTeam().getUser().getUsername().equals(authService.getLoggedUser().getUsername())) {
            throw new IllegalStateException("You have no permission");
        }
        player.setPosition(request.getPosition());
        player = playerRepository.save(player);
        return playerMapper.toPlayerView(player);
    }

    public PlayerView toggleReserve(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found"));
        if (!player.getTeam().getUser().getUsername().equals(authService.getLoggedUser().getUsername())) {
            throw new IllegalStateException("You have no permission");
        }
        player.setReserve(!player.isReserve());
        player = playerRepository.save(player);
        return playerMapper.toPlayerView(player);
    }
}
