package com.bcc.soccer.entity;

import com.bcc.soccer.dto.AddressDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String street;

    private String city;

    private String state;

    public Address(AddressDTO addressDTO) {
        this.id = addressDTO.getId();
        this.street = addressDTO.getStreet();
        this.city = addressDTO.getCity();
        this.state = addressDTO.getState();
    }
}

