package com.bcc.soccer.dto;

import com.bcc.soccer.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class PlayerDTO {

    private int id;

    private String name;

    private String position;

    private String teamName;

    private AddressDTO addressDTO;

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.position = player.getPosition();
        if(player.getTeam() != null) this.teamName = player.getTeam().getName();
        if(player.getAddress() != null) this.addressDTO = new AddressDTO(player.getAddress());
    }
}
