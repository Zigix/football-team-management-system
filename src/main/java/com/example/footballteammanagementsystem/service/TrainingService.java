package com.example.footballteammanagementsystem.service;

import com.example.footballteammanagementsystem.domain.dto.CreateTrainingRequest;
import com.example.footballteammanagementsystem.domain.dto.TrainingView;
import com.example.footballteammanagementsystem.domain.exception.TrainingNotFoundException;
import com.example.footballteammanagementsystem.domain.mapper.TrainingMapper;
import com.example.footballteammanagementsystem.domain.model.Training;
import com.example.footballteammanagementsystem.domain.model.User;
import com.example.footballteammanagementsystem.repository.TrainingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public TrainingView get(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException("Training with id " + id + " not found"));
        return trainingMapper.toTrainingView(training);
    }

    @Transactional(readOnly = true)
    public List<TrainingView> getAll() {
        User user = authService.getLoggedUser();
        return trainingRepository.findAllByUserId(user.getId())
                .stream()
                .map(trainingMapper::toTrainingView)
                .toList();
    }

    @Transactional
    public void create(CreateTrainingRequest request) {
        Training training = trainingMapper
                .toTraining(request, authService.getLoggedUser());
        trainingRepository.save(training);
    }

    @Transactional
    public void delete(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException("Training with id " + id + " not found"));
        if (!authService.getLoggedUser().getUsername().equals(training.getUser().getUsername())) {
            throw new IllegalStateException("You have no permission");
        }
        trainingRepository.delete(training);
    }
}
