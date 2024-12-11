package com.bcc.soccer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class TeamPlayerCountDTO {

    private String teamName;

    private Long playerCount;
}
