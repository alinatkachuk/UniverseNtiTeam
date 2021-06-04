package com.alinatkachuk.universeNtiTeam.controller;

import com.alinatkachuk.universeNtiTeam.model.Lord;
import com.alinatkachuk.universeNtiTeam.model.Planet;
import com.alinatkachuk.universeNtiTeam.repository.LordRepository;
import com.alinatkachuk.universeNtiTeam.repository.PlanetRepository;
import com.alinatkachuk.universeNtiTeam.service.LordService;
import com.alinatkachuk.universeNtiTeam.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class LordController {

    private static Lord lordForAddingPlanet = null;
    private final LordService lordService;
    private final PlanetService planetService;
    private PlanetRepository planetRepository;
    private LordRepository lordRepository;

    @Autowired
    public LordController (LordService lordService,
                           PlanetService planetService,
                           PlanetRepository planetRepository,
                           LordRepository lordRepository) {
        this.lordService = lordService;
        this.planetService = planetService;
        this.planetRepository = planetRepository;
        this.lordRepository = lordRepository;
    }

    @GetMapping("/")
    public String homePage () {
        return "homePage";
    }

    @GetMapping ("/lords/add")
    public String addLordPage (Model model) {
        model.addAttribute ("lord", new Lord());
        return "addLordPage";
    }

    @PostMapping("/lords/add/new")
    public String addLord (@ModelAttribute ("lord") Lord lord) {
        lordService.saveLord (lord);
        lordForAddingPlanet = lord;
        return "redirect:/planets/question";
    }

    @GetMapping ("/planets/question")
    public String joinQuestion () {
        return "joinQuestionPage";
    }

    @GetMapping ("/planets/join")
    public String choosePlanetForLord (Model model) {
        model.addAttribute ("planetsWithoutLord", planetRepository.findAllByLordIsNull ());
        return "addPlanetToLordPage";
    }

    @PostMapping ("/planets/join/new")
    public String joinPlanetToLordPage (@ModelAttribute ("planet") Planet planet) {
        planet.setLord (lordForAddingPlanet);
        planetService.editPlanet (planet.getId (), planet);
        lordService.editLord (lordForAddingPlanet.getId (), lordForAddingPlanet);
        return "redirect:/";
    }

    @GetMapping ("/lords/without")
    public String lordsWithoutPlanets (Model model) {
        model.addAttribute ("lordsWithoutPlanets", lordRepository.findAllByPlanetsIsNull ());
        return "lordsWithoutPlanetsPage";
    }

    @GetMapping ("/lords/youngest")
    public String topTenYoungestLords (Model model) {
        model.addAttribute ("topTenYoungestLords", lordRepository.findTop10ByOrderByAgeAsc ());
        return "topTenYoungestLordsPage";
    }
}
