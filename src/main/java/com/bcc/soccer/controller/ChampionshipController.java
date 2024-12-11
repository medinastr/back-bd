package com.bcc.soccer.controller;

import com.bcc.soccer.dto.ChampionshipDTO;
import com.bcc.soccer.service.ChampionshipService;
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
@RequestMapping("/champs")
public class ChampionshipController {

    private final ChampionshipService championshipService;

    @Autowired
    public ChampionshipController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    @PostMapping
    public ResponseEntity<ChampionshipDTO> createChampionship(@RequestBody ChampionshipDTO championshipDTO) {
        ChampionshipDTO dbChamp = championshipService.createChampionship(championshipDTO);
        return ResponseEntity.status(201).body(dbChamp);
    }

    @GetMapping
    public ResponseEntity<List<ChampionshipDTO>> findAllChampionships() {
        List<ChampionshipDTO> dbChamps = championshipService.findAllChampionships();
        return ResponseEntity.status(200).body(dbChamps);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampionshipDTO> updateChampionship(@PathVariable int id, @RequestBody ChampionshipDTO championshipDTO) {
        ChampionshipDTO updatedChamp = championshipService.updateChampionship(id, championshipDTO);
        return ResponseEntity.status(200).body(updatedChamp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChampionship(@PathVariable int id) {
        championshipService.deleteChampionship(id);
        return ResponseEntity.status(204).build();
    }
}
