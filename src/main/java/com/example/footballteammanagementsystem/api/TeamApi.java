package com.example.footballteammanagementsystem.api;

import com.example.footballteammanagementsystem.domain.dto.TeamView;
import com.example.footballteammanagementsystem.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamApi {
    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamView>> getAll() {
        return ResponseEntity.ok(teamService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamView> get(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.get(id));
    }
}
