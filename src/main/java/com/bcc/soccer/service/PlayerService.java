package com.bcc.soccer.service;

import com.bcc.soccer.dto.PlayerDTO;
import com.bcc.soccer.dto.PlayerTeamDTO;
import com.bcc.soccer.dto.PlayersPositionDTO;
import com.bcc.soccer.dto.TeamPlayerCountDTO;
import com.bcc.soccer.entity.Player;
import com.bcc.soccer.entity.Team;
import com.bcc.soccer.exception.ObjectNotFoundException;
import com.bcc.soccer.repository.PlayerRepository;
import com.bcc.soccer.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Team team = teamRepository.findByName(playerDTO.getTeamName())
                .orElseThrow(() -> new ObjectNotFoundException("Team name not exists."));
        Player player = new Player(playerDTO);
        player.setTeam(team);
        System.out.println(player);
        return new PlayerDTO(playerRepository.save(player));
    }

    public List<PlayerDTO> findAllPlayers() {
        return playerRepository.findAll().stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> findAllBySameStadiumAddress(int stadiumId) {
        return playerRepository.findAllBySameStadiumAddress(stadiumId).stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public List<TeamPlayerCountDTO> countPlayersByTeam() {
        return playerRepository.countPlayersByTeam();
    }

    public List<PlayerDTO> findByFirstLetter(String letter) {
        return playerRepository.findByFirstLetter(letter).stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> findPlayersByNameContaining(String search) {
        return playerRepository.findPlayersByNameContaining(search).stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> findPlayersByPositionEndingWith(String suffix) {
        return playerRepository.findPlayersByPositionEndingWith(suffix).stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> findPlayersByNameLength(int length) {
        return playerRepository.findPlayersByNameLength(length).stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public Long howManyPlayers() {
        return playerRepository.countTotalPlayers();
    }

    public List<PlayersPositionDTO> countPlayersByPosition() {
        return playerRepository.countPlayersByPosition();
    }

    public List<PlayerDTO> findAllPlayersOrderedByName() {
        return playerRepository.findAllPlayersOrderedByName().stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> findAllPlayersOrderedByTeamAndName() {
        return playerRepository.findAllPlayersOrderedByTeamAndName().stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> findAllPlayersOrderedByPositionDesc() {
        return playerRepository.findAllPlayersOrderedByPositionDesc().stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public List<PlayerTeamDTO> findAllPlayersWithTeamName() {
        return playerRepository.findAllPlayersWithTeamName();
    }

    public PlayerDTO updatePlayer(int id, PlayerDTO playerDTO) {
        if(!playerRepository.existsById(id)) throw new ObjectNotFoundException("Player not exists.");
        Player player = new Player(playerDTO);
        player.setId(id);
        Team team = teamRepository.findByName(playerDTO.getTeamName())
                .orElseThrow(() -> new ObjectNotFoundException("Team name not exists"));
        player.setTeam(team);
        return new PlayerDTO(playerRepository.save(player));
    }

    public void deleteTeam(int id) {
        if(!playerRepository.existsById(id)) throw new ObjectNotFoundException("Player already not exists.");
        playerRepository.deleteById(id);
    }
}
