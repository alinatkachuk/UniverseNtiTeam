package com.alinatkachuk.universeNtiTeam.model;

import javax.persistence.*;

@Entity
@Table(name="planets")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planetName;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "lord_id")
    private Lord lord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public Lord getLord() {
        return lord;
    }

    public void setLord(Lord lord) {
        this.lord = lord;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", planetName='" + planetName + '\'' +
                ", lord=" + lord +
                '}';
    }
}
