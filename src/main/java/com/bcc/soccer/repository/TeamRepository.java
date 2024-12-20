package com.bcc.soccer.repository;

import com.bcc.soccer.dto.PlayerTeamDTO;
import com.bcc.soccer.dto.TeamStadiumDTO;
import com.bcc.soccer.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findByName(String name);

    @Query("SELECT t FROM Team t WHERE t.stadium.capacity > :capacity")
    List<Team> findTeamsByStadiumCapacityGreaterThan(@Param("capacity") Integer capacity);

    @Query("SELECT t FROM Team t " +
            "WHERE (SELECT COUNT(p) FROM Player p WHERE p.team.id = t.id) > :minPlayers")
    List<Team> findTeamsWithMoreThanXPlayers(@Param("minPlayers") Integer minPlayers);

    @Query("SELECT t FROM Team t ORDER BY t.foundedYear ASC")
    List<Team> findAllTeamsOrderedByFoundationYear();

    @Query("SELECT new com.bcc.soccer.dto.TeamStadiumDTO(t.name, s.name) " +
            "FROM Team t JOIN t.stadium s")
    List<TeamStadiumDTO> findAllTeamsWithStadiumName();
}
