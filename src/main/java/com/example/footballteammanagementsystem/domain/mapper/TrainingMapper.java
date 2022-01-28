package com.example.footballteammanagementsystem.domain.mapper;

import com.example.footballteammanagementsystem.domain.dto.CreateTrainingRequest;
import com.example.footballteammanagementsystem.domain.dto.TrainingView;
import com.example.footballteammanagementsystem.domain.model.Training;
import com.example.footballteammanagementsystem.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainingMapper {

    @Mapping(target = "trainer", source = "user.username")
    TrainingView toTrainingView(Training training);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "topic", source = "request.topic")
    @Mapping(target = "description", source = "request.description")
    @Mapping(target = "date", source = "request.date")
    @Mapping(target = "user", source = "user")
    Training toTraining(CreateTrainingRequest request, User user);
}
