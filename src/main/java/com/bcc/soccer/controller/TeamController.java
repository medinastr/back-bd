package com.bcc.soccer.controller;

import com.bcc.soccer.dto.TeamDTO;
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
