package com.bcc.soccer.repository;

import com.bcc.soccer.dto.PlayersPositionDTO;
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

    @Query("SELECT p FROM Player p WHERE p.name LIKE :letter%")
    List<Player> findByFirstLetter(@Param("letter") String letter);

    @Query("SELECT p FROM Player p WHERE p.name LIKE %:searchString%")
    List<Player> findPlayersByNameContaining(@Param("searchString") String searchString);

    @Query("SELECT p FROM Player p WHERE p.position LIKE %:suffix")
    List<Player> findPlayersByPositionEndingWith(@Param("suffix") String suffix);

    @Query("SELECT p FROM Player p WHERE LENGTH(p.name) = :length")
    List<Player> findPlayersByNameLength(@Param("length") int length);

    @Query("SELECT COUNT(p) FROM Player p")
    long countTotalPlayers();

    @Query("SELECT new com.bcc.soccer.dto.PlayersPositionDTO(p.position, COUNT(p)) " +
            "FROM Player p " +
            "GROUP BY p.position")
    List<PlayersPositionDTO> countPlayersByPosition();

}
