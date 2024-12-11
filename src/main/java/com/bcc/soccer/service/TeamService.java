package com.bcc.soccer.service;

import com.bcc.soccer.dto.PlayerDTO;
import com.bcc.soccer.dto.TeamDTO;
import com.bcc.soccer.dto.TeamStadiumDTO;
import com.bcc.soccer.entity.Team;
import com.bcc.soccer.exception.ObjectNotFoundException;
import com.bcc.soccer.repository.PlayerRepository;
import com.bcc.soccer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
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

    public List<PlayerDTO> findAllTeamPlayers(int id) {
        return playerRepository.findAllByTeamId(id).stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public List<TeamDTO> findTeamByStadiumCapacity(Integer capacity) {
        return teamRepository.findTeamsByStadiumCapacityGreaterThan(capacity).stream()
                .map(TeamDTO::new)
                .collect(Collectors.toList());
    }

    public List<TeamDTO> findTeamsWithMoreThanXPlayers(Integer min) {
        return teamRepository.findTeamsWithMoreThanXPlayers(min).stream()
                .map(TeamDTO::new)
                .collect(Collectors.toList());
    }

    public List<TeamDTO> findAllTeamsOrderedByFoundationYear() {
        return teamRepository.findAllTeamsOrderedByFoundationYear().stream()
                .map(TeamDTO::new)
                .collect(Collectors.toList());
    }

    public List<TeamStadiumDTO> findAllTeamsWithStadiumName() {
        return teamRepository.findAllTeamsWithStadiumName();
    }

    public TeamDTO updateTeam(int id, TeamDTO teamDTO) {
        Team dbTeam = teamRepository.findById(id)
                .orElseThrow(ObjectNotFoundException::new);
        dbTeam.setName(teamDTO.getName());
        dbTeam.setFoundedYear(teamDTO.getFoundedYear());
        dbTeam.setFifaRanking(teamDTO.getFifaRanking());
        dbTeam.setMarketValue(teamDTO.getMarketValue());
        return new TeamDTO(teamRepository.save(dbTeam));
    }

    public void deleteTeam(int id) {
        if(!teamRepository.existsById(id)) throw new ObjectNotFoundException("Team already not exists.");
        teamRepository.deleteById(id);
    }
}
