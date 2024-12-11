package com.bcc.soccer.controller;

import com.bcc.soccer.dto.AddressDTO;
import com.bcc.soccer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/used")
    public ResponseEntity<List<AddressDTO>> findAllAddressesUsedByStadiumsOrPlayers() {
        List<AddressDTO> dbAddresses = addressService.findAllAddressesUsedByStadiumsOrPlayers();
        return ResponseEntity.status(200).body(dbAddresses);
    }

    @GetMapping("/different")
    public ResponseEntity<List<AddressDTO>> findStadiumAddressesNotUsedByPlayers() {
        List<AddressDTO> dbAddresses = addressService.findStadiumAddressesNotUsedByPlayers();
        return ResponseEntity.status(200).body(dbAddresses);
    }

    @GetMapping("/exclusive")
    public ResponseEntity<List<AddressDTO>> findAddressesExclusiveToStadiumsOrPlayers() {
        List<AddressDTO> dbAddresses = addressService.findAddressesExclusiveToStadiumsOrPlayers();
        return ResponseEntity.status(200).body(dbAddresses);
    }
}
