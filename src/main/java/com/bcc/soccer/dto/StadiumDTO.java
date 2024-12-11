package com.bcc.soccer.dto;

import com.bcc.soccer.entity.Stadium;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class StadiumDTO {

    private int id;

    private String name;

    private Integer capacity;

    private Integer builtYear;

    private String owner;

    private String teamName;

    private AddressDTO addressDTO;

    public StadiumDTO(Stadium stadium) {
        this.id = stadium.getId();
        this.name = stadium.getName();
        this.capacity = stadium.getCapacity();
        this.builtYear = stadium.getBuiltYear();
        this.owner = stadium.getOwner();
        if(stadium.getAddress() != null) this.addressDTO = new AddressDTO(stadium.getAddress());
    }
}
