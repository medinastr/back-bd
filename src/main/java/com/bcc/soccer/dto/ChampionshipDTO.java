package com.bcc.soccer.dto;

import com.bcc.soccer.entity.Championship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ChampionshipDTO {

    private int id;

    private String name;

    public ChampionshipDTO(Championship championship) {
        this.id = championship.getId();
        this.name = championship.getName();
    }
}
