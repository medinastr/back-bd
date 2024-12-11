package com.bcc.soccer.entity;

import com.bcc.soccer.dto.TeamDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "founded_year")
    private Integer foundedYear;

    @Column(name = "fifa_ranking")
    private Integer fifaRanking;

    @Column(name = "market_value")
    private Integer marketValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Player> players;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="team_championship",
            joinColumns = @JoinColumn(name="team_id"),
            inverseJoinColumns = @JoinColumn(name="championship_id")
    )
    private List<Championship> championships;

    public Team(TeamDTO teamDTO) {
        this.id = teamDTO.getId();
        this.name = teamDTO.getName();
        this.foundedYear = teamDTO.getFoundedYear();
        this.fifaRanking = teamDTO.getFifaRanking();
        this.marketValue = teamDTO.getMarketValue();
    }
}

