package com.alinatkachuk.universeNtiTeam.service;

import com.alinatkachuk.universeNtiTeam.model.Lord;
import com.alinatkachuk.universeNtiTeam.model.Planet;
import org.springframework.http.ResponseEntity;

public interface PlanetService {

    Planet savePlanet (Planet planet);

    ResponseEntity<?> deletePlanet (Long planetId);

    Planet editPlanet (Long planetId, Planet planet);

}
