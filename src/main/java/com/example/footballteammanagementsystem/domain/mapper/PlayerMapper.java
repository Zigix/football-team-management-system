package com.example.footballteammanagementsystem.domain.mapper;

import com.example.footballteammanagementsystem.domain.dto.AddPlayerRequest;
import com.example.footballteammanagementsystem.domain.dto.PlayerView;
import com.example.footballteammanagementsystem.domain.model.Player;
import com.example.footballteammanagementsystem.domain.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", source = "request.firstName")
    @Mapping(target = "lastName", source = "request.lastName")
    @Mapping(target = "nationality", source = "request.nationality")
    @Mapping(target = "dateOfBirth", source = "request.dateOfBirth")
    @Mapping(target = "number", source = "request.number")
    @Mapping(target = "position", source = "request.position")
    @Mapping(target = "joinDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "reserve", constant = "true")
    Player toPlayer(AddPlayerRequest request, Team team);

    @Mapping(target = "teamName", expression = "java(player.getTeam().getName())")
    @Mapping(target = "teamId", expression = "java(player.getTeam().getId())")
    PlayerView toPlayerView(Player player);
}
