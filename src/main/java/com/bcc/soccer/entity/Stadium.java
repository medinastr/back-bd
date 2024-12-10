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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Stadium(StadiumDTO stadiumDTO) {
        this.id = stadiumDTO.getId();
        this.name = stadiumDTO.getName();
        this.capacity = stadiumDTO.getCapacity();
        if(stadiumDTO.getAddressDTO() != null) this.address = new Address(stadiumDTO.getAddressDTO());
    }
}

