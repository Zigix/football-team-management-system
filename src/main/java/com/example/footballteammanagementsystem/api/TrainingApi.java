package com.example.footballteammanagementsystem.api;

import com.example.footballteammanagementsystem.domain.dto.CreateTrainingRequest;
import com.example.footballteammanagementsystem.domain.dto.TrainingView;
import com.example.footballteammanagementsystem.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings")
@RequiredArgsConstructor
public class TrainingApi {
    private final TrainingService trainingService;

    @GetMapping("/{id}")
    public ResponseEntity<TrainingView> get(@PathVariable Long id) {
        return ResponseEntity.ok(trainingService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<TrainingView>> getAll() {
        return ResponseEntity.ok(trainingService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTrainingRequest request) {
        trainingService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trainingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
