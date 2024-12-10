package com.bcc.soccer.dto;

import com.bcc.soccer.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class AddressDTO {

    private int id;

    private String street;

    private String city;

    private String state;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state = address.getState();
    }
}
