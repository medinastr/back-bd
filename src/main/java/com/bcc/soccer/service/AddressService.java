package com.bcc.soccer.service;

import com.bcc.soccer.dto.AddressDTO;
import com.bcc.soccer.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressDTO> findAllAddressesUsedByStadiumsOrPlayers() {
        return addressRepository.findAllAddressesUsedByStadiumsOrPlayers().stream()
                .map(AddressDTO::new)
                .collect(Collectors.toList());
    }

    public List<AddressDTO> findStadiumAddressesNotUsedByPlayers() {
        return addressRepository.findStadiumAddressesNotUsedByPlayers().stream()
                .map(AddressDTO::new)
                .collect(Collectors.toList());
    }

    public List<AddressDTO> findAddressesExclusiveToStadiumsOrPlayers() {
        return addressRepository.findAddressesExclusiveToStadiumsOrPlayers().stream()
                .map(AddressDTO::new)
                .collect(Collectors.toList());
    }
}
