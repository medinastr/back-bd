package com.bcc.soccer.repository;

import com.bcc.soccer.dto.TeamPlayerCountDTO;
import com.bcc.soccer.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findAllByTeamId(int id);

    @Query("SELECT p FROM Player p " +
            "WHERE p.address.id = (SELECT s.address.id FROM Stadium s WHERE s.id = :stadiumId)")
    List<Player> findAllBySameStadiumAddress(@Param("stadiumId") int stadiumId);

    @Query("SELECT new com.bcc.soccer.dto.TeamPlayerCountDTO(p.team.name, COUNT(p)) " +
            "FROM Player p " +
            "GROUP BY p.team.name")
    List<TeamPlayerCountDTO> countPlayersByTeam();
}
