package com.alinatkachuk.universeNtiTeam.controller;

import com.alinatkachuk.universeNtiTeam.model.Planet;
import com.alinatkachuk.universeNtiTeam.repository.PlanetRepository;
import com.alinatkachuk.universeNtiTeam.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlanetController {

    private final PlanetService planetService;
    private PlanetRepository planetRepository;

    @Autowired
    public PlanetController (PlanetService planetService,
                             PlanetRepository planetRepository) {
        this.planetService = planetService;
        this.planetRepository = planetRepository;
    }

    @GetMapping("/planets/add")
    public String addPlanetPage (Model model) {
        model.addAttribute ("planet", new Planet ());
        return "addPlanetPage";
    }

    @PostMapping("/planets/add/new")
    public String addPlanet (@ModelAttribute("planet") Planet planet) {
        planetService.savePlanet (planet);
        return "redirect:/planets/question";
    }

    @GetMapping("/planets/add")
    public String deletePlanetPage (Model model) {
        model.addAttribute ("planets", planetRepository.findAll ());
        return "deletePlanetPage";
    }

    @PostMapping("/planets/delete/bye")
    public String deletePlanet (@ModelAttribute("planet") Planet planet) {
        planetService.deletePlanet (planet.getId ());
        return "redirect:/";
    }

}
