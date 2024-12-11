package com.bcc.soccer.controller;

import com.bcc.soccer.dto.PlayerDTO;
import com.bcc.soccer.dto.TeamDTO;
import com.bcc.soccer.entity.Player;
import com.bcc.soccer.entity.Team;
import com.bcc.soccer.service.TeamService;
import jdk.dynalink.linker.LinkerServices;
import jdk.javadoc.doclet.Reporter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        TeamDTO dbTeam = teamService.createTeam(teamDTO);
        return ResponseEntity.status(201).body(dbTeam);
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> findAllTeams() {
        List<TeamDTO> teams = teamService.findAllTeams();
        return ResponseEntity.status(200).body(teams);
    }

    @GetMapping("/{id}/players")
    public ResponseEntity<List<PlayerDTO>> findAllTeamPlayers(@PathVariable int id) {
        List<PlayerDTO> dbPlayers = teamService.findAllTeamPlayers(id);
        return ResponseEntity.status(200).body(dbPlayers);
    }

    @GetMapping("/capacity-greater-than/{capacity}")
    public ResponseEntity<List<TeamDTO>> findTeamByStadiumCapacity(Integer capacity) {
        List<TeamDTO> dbTeams = teamService.findTeamByStadiumCapacity(capacity);
        return ResponseEntity.status(200).body(dbTeams);
    }

    @GetMapping("/min-players/{min}")
    public ResponseEntity<List<TeamDTO>> findTeamsWithMoreThanXPlayers(Integer min) {
        List<TeamDTO> dbTeams = teamService.findTeamsWithMoreThanXPlayers(min);
        return ResponseEntity.status(200).body(dbTeams);
    }

    @GetMapping("/order-year")
    public ResponseEntity<List<TeamDTO>> findAllTeamsOrderedByFoundationYear() {
        List<TeamDTO> dbTeams = teamService.findAllTeamsOrderedByFoundationYear();
        return ResponseEntity.status(200).body(dbTeams);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable int id, @RequestBody TeamDTO teamDTO) {
        TeamDTO updatedTeam = teamService.updateTeam(id, teamDTO);
        return ResponseEntity.status(200).body(updatedTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable int id) {
        teamService.deleteTeam(id);
        return ResponseEntity.status(204).build();
    }
}
