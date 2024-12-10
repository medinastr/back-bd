package com.bcc.soccer.dto;

import com.bcc.soccer.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class TeamDTO {

    private int id;

    private String name;

    private Integer foundedYear;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.foundedYear = team.getFoundedYear();
    }
}
