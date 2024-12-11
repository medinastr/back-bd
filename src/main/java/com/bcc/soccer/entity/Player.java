package com.bcc.soccer.entity;

import com.bcc.soccer.dto.AddressDTO;
import com.bcc.soccer.dto.PlayerDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String position;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Player(PlayerDTO playerDTO) {
        this.id = playerDTO.getId();;
        this.name = playerDTO.getName();
        this.position = playerDTO.getPosition();
        if(playerDTO.getAddressDTO() != null) this.address = new Address(playerDTO.getAddressDTO());
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", team=" + team.getName() +
                '}';
    }
}

