package com.bcc.soccer.service;

import com.bcc.soccer.dto.TeamDTO;
import com.bcc.soccer.entity.Team;
import com.bcc.soccer.exception.ObjectNotFoundException;
import com.bcc.soccer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamDTO createTeam(TeamDTO teamDTO) {
        Team team = new Team(teamDTO);
        return new TeamDTO(teamRepository.save(team));
    }

    public List<TeamDTO> findAllTeams() {
        return teamRepository.findAll().stream()
                .map(TeamDTO::new)
                .collect(Collectors.toList());
    }

    public TeamDTO updateTeam(int id, TeamDTO teamDTO) {
        Team dbTeam = teamRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
        dbTeam.setName(teamDTO.getName());
        dbTeam.setFoundedYear(teamDTO.getFoundedYear());
        return new TeamDTO(teamRepository.save(dbTeam));
    }

    public void deleteTeam(int id) {
        if(!teamRepository.existsById(id)) throw new ObjectNotFoundException("Team already not exists.");
        teamRepository.deleteById(id);
    }
}
