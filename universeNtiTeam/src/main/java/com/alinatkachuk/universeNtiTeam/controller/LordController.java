package com.alinatkachuk.universeNtiTeam.controller;

import com.alinatkachuk.universeNtiTeam.model.Lord;
import com.alinatkachuk.universeNtiTeam.model.Planet;
import com.alinatkachuk.universeNtiTeam.repository.LordRepository;
import com.alinatkachuk.universeNtiTeam.repository.PlanetRepository;
import com.alinatkachuk.universeNtiTeam.service.LordService;
import com.alinatkachuk.universeNtiTeam.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LordController {

    private final LordService lordService;
    private final PlanetService planetService;
    private final PlanetRepository planetRepository;
    private final LordRepository lordRepository;

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

    @GetMapping
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
        return "redirect:/planets/question";
    }

    @GetMapping ("/planets/question")
    public String joinQuestion () {
        return "joinQuestionPage";
    }

    @GetMapping ("/planets/join")
    public String choosePlanetForLord (Model model) {
        model.addAttribute ("planetsWithoutLord", planetRepository.findByLordIsNull ());
        model.addAttribute ("lords", lordRepository.findAll ());
        return "addPlanetToLordPage";
    }

    @PostMapping ("/planets/join/new")
    public String joinPlanetToLord (@ModelAttribute ("planetId") Long planetId,
                                        @ModelAttribute ("lordId") Long lordId) {
        Planet planet = planetRepository.findById (planetId).orElse(new Planet());
        Lord lord = lordRepository.findById (lordId).orElse(new Lord ());
        planet.setLord (lord);
        planetService.editPlanet (planet.getId (), planet);
        lordService.editLord (lord.getId (), lord);
        return "redirect:/";
    }

    @GetMapping ("/lords/without")
    public String lordsWithoutPlanets (Model model) {
        model.addAttribute ("lordsWithoutPlanets", lordRepository.findByPlanetsIsNull ());
        return "lordsWithoutPlanetsPage";
    }

    @GetMapping ("/lords/youngest")
    public String topTenYoungestLords (Model model) {
        model.addAttribute ("topTenYoungestLords", lordRepository.findTop10ByOrderByAgeAsc ());
        return "topTenYoungestLordsPage";
    }
}
