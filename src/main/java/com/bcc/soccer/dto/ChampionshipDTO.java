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

    private Integer edition;

    private String startDate;

    private String endDate;

    public ChampionshipDTO(Championship championship) {
        this.id = championship.getId();
        this.name = championship.getName();
        this.edition = championship.getEdition();
        this.startDate = championship.getStartDate();
        this.endDate = championship.getEndDate();
    }
}
