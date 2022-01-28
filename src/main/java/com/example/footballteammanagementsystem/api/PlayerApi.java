package com.example.footballteammanagementsystem.api;

import com.example.footballteammanagementsystem.domain.dto.AddPlayerRequest;
import com.example.footballteammanagementsystem.domain.dto.ChangePlayerPositionRequest;
import com.example.footballteammanagementsystem.domain.dto.PlayerView;
import com.example.footballteammanagementsystem.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerApi {
    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody AddPlayerRequest request) {
        playerService.add(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerView> get(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.get(id));
    }

    @GetMapping("/by-team/{id}")
    public ResponseEntity<List<PlayerView>> getByTeam(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getByTeam(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/position")
    public ResponseEntity<PlayerView> changePosition(@RequestBody ChangePlayerPositionRequest request) {
        return ResponseEntity.ok(playerService.changePosition(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerView> toggleReserve(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.toggleReserve(id));
    }
}
