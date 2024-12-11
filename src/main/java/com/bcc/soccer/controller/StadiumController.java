package com.bcc.soccer.controller;

import com.bcc.soccer.dto.PlayerDTO;
import com.bcc.soccer.dto.StadiumDTO;
import com.bcc.soccer.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stadiums")
public class StadiumController {

    private final StadiumService stadiumService;

    @Autowired
    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @PostMapping
    public ResponseEntity<StadiumDTO> createStadium(@RequestBody StadiumDTO stadiumDTO) {
        StadiumDTO dbStadium = stadiumService.createStadium(stadiumDTO);
        return ResponseEntity.status(201).body(dbStadium);
    }

    @GetMapping
    public ResponseEntity<List<StadiumDTO>> findAllStadiums() {
        List<StadiumDTO> dbStadiums = stadiumService.findAllStadiums();
        return ResponseEntity.status(200).body(dbStadiums);
    }

    @GetMapping("/after-year/{year}")
    public ResponseEntity<List<StadiumDTO>> findAllTeamsStadiumAfter(@PathVariable int year) {
        List<StadiumDTO> dbStadiums = stadiumService.findAllTeamsStadiumAfter(year);
        return ResponseEntity.status(200).body(dbStadiums);
    }

    @GetMapping("/search-in-name/{search}")
    public ResponseEntity<List<StadiumDTO>> findStadiumsByNameContaining(String search) {
        List<StadiumDTO> dbStadiums = stadiumService.findStadiumsByNameContaining(search);
        return ResponseEntity.status(200).body(dbStadiums);
    }

    @GetMapping("/capacity-avg")
    public ResponseEntity<Double> calculateAverageStadiumCapacity() {
        Double avg = stadiumService.calculateAverageStadiumCapacity();
        return ResponseEntity.status(200).body(avg);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StadiumDTO> updateStadium(@PathVariable int id, @RequestBody StadiumDTO stadiumDTO) {
        StadiumDTO updatedStadium = stadiumService.updateStadium(id, stadiumDTO);
        return ResponseEntity.status(200).body(updatedStadium);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStadium(@PathVariable int id) {
        stadiumService.deleteStadium(id);
        return ResponseEntity.status(204).build();
    }
}
