package com.bcc.soccer.entity;

import com.bcc.soccer.dto.StadiumDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Integer capacity;

    @Column(name = "built_year")
    private Integer builtYear;

    private String owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    public Stadium(StadiumDTO stadiumDTO) {
        this.id = stadiumDTO.getId();
        this.name = stadiumDTO.getName();
        this.capacity = stadiumDTO.getCapacity();
        this.builtYear = stadiumDTO.getBuiltYear();
        this.owner = stadiumDTO.getOwner();
        if(stadiumDTO.getAddressDTO() != null) this.address = new Address(stadiumDTO.getAddressDTO());
    }
}

