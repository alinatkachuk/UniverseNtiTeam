package com.alinatkachuk.universeNtiTeam.service;

import com.alinatkachuk.universeNtiTeam.exception.ResourceNotFoundException;
import com.alinatkachuk.universeNtiTeam.model.Lord;
import com.alinatkachuk.universeNtiTeam.model.Planet;
import com.alinatkachuk.universeNtiTeam.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class PlanetServiceImpl implements PlanetService{

    PlanetRepository planetRepository;

    @Autowired
    public PlanetServiceImpl (PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    public Planet savePlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    @Override
    public ResponseEntity<?> deletePlanet(Long planetId) {
        return planetRepository.findById(planetId).map(planet -> {
            planetRepository.delete(planet);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException ("PlanetId " + planetId + " not found"));
    }

    @Override
    public Planet editPlanet(Long planetId, Planet planet) {
        return planetRepository.findById (planetId).map(updatedPlanet -> {
            updatedPlanet.setLord (planet.getLord ());
            return planetRepository.save(updatedPlanet);
        }).orElseThrow (() -> new ResourceNotFoundException ("PlanetId " + planetId + " not found"));
    }
}
