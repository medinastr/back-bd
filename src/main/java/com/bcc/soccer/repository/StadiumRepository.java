package com.bcc.soccer.repository;

import com.bcc.soccer.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Integer> {

    @Query("SELECT s " +
            "FROM Stadium s " +
            "WHERE s.id IN (SELECT t.stadium.id FROM Team t WHERE t.foundedYear > :year)")
    List<Stadium> findAllTeamsStadiumAfter(@Param("year") int year);

    @Query("SELECT s FROM Stadium s WHERE s.name LIKE %:searchString%")
    List<Stadium> findStadiumsByNameContaining(@Param("searchString") String searchString);
}
