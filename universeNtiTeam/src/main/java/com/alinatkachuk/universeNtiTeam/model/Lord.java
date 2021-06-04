package com.alinatkachuk.universeNtiTeam.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="lords")
public class Lord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @OneToMany (mappedBy = "lord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Planet> planets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    @Override
    public String toString() {
        return "Lord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", planets=" + planets +
                '}';
    }
}
