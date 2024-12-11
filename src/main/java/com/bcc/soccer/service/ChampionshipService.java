package com.bcc.soccer.service;

import com.bcc.soccer.dto.ChampionshipDTO;
import com.bcc.soccer.entity.Championship;
import com.bcc.soccer.exception.ObjectNotFoundException;
import com.bcc.soccer.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionshipService {

    private final ChampionshipRepository championshipRepository;

    @Autowired
    public ChampionshipService(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    public ChampionshipDTO createChampionship(ChampionshipDTO championshipDTO) {
        Championship championship = new Championship(championshipDTO);
        return new ChampionshipDTO(championshipRepository.save(championship));
    }

    public List<ChampionshipDTO> findAllChampionships() {
        return championshipRepository.findAll().stream()
                .map(ChampionshipDTO::new)
                .collect(Collectors.toList());
    }

    public ChampionshipDTO updateChampionship(int id, ChampionshipDTO championshipDTO) {
        Championship championship = championshipRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Championship not exists."));
        championship.setName(championshipDTO.getName());
        return new ChampionshipDTO(championshipRepository.save(championship));
    }

    public void deleteChampionship(int id) {
        if(!championshipRepository.existsById(id)) throw new ObjectNotFoundException("Championship already not exists.");
        championshipRepository.deleteById(id);
    }
}
