package com.bcc.soccer.controller;

import com.bcc.soccer.dto.PlayerDTO;
import com.bcc.soccer.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        PlayerDTO dbPlayer = playerService.createPlayer(playerDTO);
        return ResponseEntity.status(201).body(dbPlayer);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAllPlayers() {
        List<PlayerDTO> dbPlayers = playerService.findAllPlayers();
        return ResponseEntity.status(200).body(dbPlayers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable int id, @RequestBody PlayerDTO playerDTO) {
        PlayerDTO dbPlayer = playerService.updatePlayer(id, playerDTO);
        return ResponseEntity.status(200).body(dbPlayer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable int id) {
        playerService.deleteTeam(id);
        return ResponseEntity.status(204).build();
    }
}
