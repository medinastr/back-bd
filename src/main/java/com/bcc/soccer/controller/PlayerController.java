package com.bcc.soccer.controller;

import com.bcc.soccer.dto.PlayerDTO;
import com.bcc.soccer.dto.StadiumDTO;
import com.bcc.soccer.dto.TeamPlayerCountDTO;
import com.bcc.soccer.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
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

    @GetMapping("/same-stadium-address/{stadiumId}")
    public ResponseEntity<List<PlayerDTO>> findAllBySameStadiumAddress(@PathVariable int stadiumId) {
        List<PlayerDTO> dbPlayers = playerService.findAllBySameStadiumAddress(stadiumId);
        return ResponseEntity.status(200).body(dbPlayers);
    }

    @GetMapping("/count-players-by-team")
    public ResponseEntity<List<TeamPlayerCountDTO>> countPlayersByTeam() {
        List<TeamPlayerCountDTO> counts = playerService.countPlayersByTeam();
        return ResponseEntity.status(200).body(counts);
    }

    @GetMapping("/first-letter/{letter}")
    public ResponseEntity<List<PlayerDTO>> findByFirstLetter(@PathVariable String letter) {
        List<PlayerDTO> dbPlayers = playerService.findByFirstLetter(letter);
        return ResponseEntity.status(200).body(dbPlayers);
    }

    @GetMapping("/search-in-name/{search}")
    public ResponseEntity<List<PlayerDTO>> findPlayersByNameContaining(@PathVariable String search) {
        List<PlayerDTO> dbPlayers = playerService.findPlayersByNameContaining(search);
        return ResponseEntity.status(200).body(dbPlayers);
    }

    @GetMapping("/position-suffix/{suffix}")
    public ResponseEntity<List<PlayerDTO>> findPlayersByPositionEndingWith(@PathVariable String suffix) {
        List<PlayerDTO> dbPlayers = playerService.findPlayersByPositionEndingWith(suffix);
        return ResponseEntity.status(200).body(dbPlayers);
    }

    @GetMapping("/find-name-length/{length}")
    public ResponseEntity<List<PlayerDTO>> findPlayersByNameLength(@PathVariable int length) {
        List<PlayerDTO> dbPlayers = playerService.findPlayersByNameLength(length);
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
