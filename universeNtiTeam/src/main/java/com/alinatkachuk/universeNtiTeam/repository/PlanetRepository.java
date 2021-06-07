package com.alinatkachuk.universeNtiTeam.repository;

import com.alinatkachuk.universeNtiTeam.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    @Override
    List<Planet> findAll();

    @Override
    Optional<Planet> findById(Long planetId);

    List<Planet> findByLordIsNull ();
}
