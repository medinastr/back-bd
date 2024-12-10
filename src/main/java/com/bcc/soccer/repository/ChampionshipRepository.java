package com.bcc.soccer.repository;

import com.bcc.soccer.entity.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionshipRepository extends JpaRepository<Championship, Integer> {
}
