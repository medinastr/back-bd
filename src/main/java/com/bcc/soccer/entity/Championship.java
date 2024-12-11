package com.bcc.soccer.entity;

import com.bcc.soccer.dto.ChampionshipDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="team_championship",
            joinColumns = @JoinColumn(name="championship_id"),
            inverseJoinColumns = @JoinColumn(name="team_id"))
    private List<Team> teams;

    public Championship(ChampionshipDTO championshipDTO) {
        this.id = championshipDTO.getId();
        this.name = championshipDTO.getName();
    }
}
