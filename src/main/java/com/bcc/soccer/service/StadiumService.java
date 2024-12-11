package com.bcc.soccer.service;

import com.bcc.soccer.dto.StadiumDTO;
import com.bcc.soccer.dto.TeamDTO;
import com.bcc.soccer.entity.Stadium;
import com.bcc.soccer.entity.Team;
import com.bcc.soccer.exception.ObjectNotFoundException;
import com.bcc.soccer.repository.StadiumRepository;
import com.bcc.soccer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StadiumService {

    private final StadiumRepository stadiumRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public StadiumService(StadiumRepository stadiumRepository, TeamRepository teamRepository) {
        this.stadiumRepository = stadiumRepository;
        this.teamRepository = teamRepository;
    }

    public StadiumDTO createStadium(StadiumDTO stadiumDTO) {
        Stadium stadium = new Stadium(stadiumDTO);
        return new StadiumDTO(stadiumRepository.save(stadium));
    }

    public List<StadiumDTO> findAllStadiums() {
        return stadiumRepository.findAll().stream()
                .map(StadiumDTO::new)
                .collect(Collectors.toList());
    }

    public List<StadiumDTO> findAllTeamsStadiumAfter(int year) {
        return stadiumRepository.findAllTeamsStadiumAfter(year).stream()
                .map(StadiumDTO::new)
                .collect(Collectors.toList());
    }

    public List<StadiumDTO> findStadiumsByNameContaining(String search) {
        return stadiumRepository.findStadiumsByNameContaining(search).stream()
                .map(StadiumDTO::new)
                .collect(Collectors.toList());
    }

    public double calculateAverageStadiumCapacity() {
        return stadiumRepository.calculateAverageStadiumCapacity();
    }

    public List<StadiumDTO> findAllStadiumsOrderedByCapacityDesc() {
        return stadiumRepository.findAllStadiumsOrderedByCapacityDesc().stream()
                .map(StadiumDTO::new)
                .collect(Collectors.toList());
    }

    public StadiumDTO updateStadium(int id, StadiumDTO stadiumDTO) {
        Stadium dbStadium = stadiumRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Stadium not found."));
        dbStadium.setId(id);
        dbStadium.setName(stadiumDTO.getName());
        dbStadium.setCapacity(stadiumDTO.getCapacity());
        dbStadium.setBuiltYear(stadiumDTO.getBuiltYear());
        dbStadium.setOwner(stadiumDTO.getOwner());
        return new StadiumDTO(stadiumRepository.save(dbStadium));
    }

    public void deleteStadium(int id) {
        if(!stadiumRepository.existsById(id)) throw new ObjectNotFoundException("Stadium already not found.");
        stadiumRepository.deleteById(id);
    }
}
