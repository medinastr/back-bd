package com.bcc.soccer.repository;

import com.bcc.soccer.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT s.address FROM Stadium s UNION SELECT p.address FROM Player p")
    List<Address> findAllAddressesUsedByStadiumsOrPlayers();

    @Query("SELECT s.address FROM Stadium s WHERE s.address.id NOT IN " +
            "(SELECT p.address.id FROM Player p)")
    List<Address> findStadiumAddressesNotUsedByPlayers();

    @Query("SELECT a FROM Address a " +
            "WHERE (EXISTS (SELECT 1 FROM Stadium s WHERE s.address = a) AND " +
            "       NOT EXISTS (SELECT 1 FROM Player p WHERE p.address = a)) " +
            "   OR (EXISTS (SELECT 1 FROM Player p WHERE p.address = a) AND " +
            "       NOT EXISTS (SELECT 1 FROM Stadium s WHERE s.address = a))")
    List<Address> findAddressesExclusiveToStadiumsOrPlayers();
}
