package com.hellmetz.festival.controller;

import com.hellmetz.festival.model.*;
import com.hellmetz.festival.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired private GroupeService groupeService;
    @Autowired private ArtisteService artisteService;
    @Autowired private ConcertService concertService;
    @Autowired private SceneService sceneService;
    @Autowired private EditionService editionService;



    @GetMapping("/groupes")
    public List<Groupe> tousLesGroupes() {
        return groupeService.findAll();
    }
    @GetMapping("/groupes/{id}")
    public Groupe unGroupe(@PathVariable Long id) {
        return groupeService.findById(id);
    }



    @GetMapping("/artistes")
    public List<Artiste> tousLesArtistes() {
        return artisteService.findAll();
    }
    @GetMapping("/artistes/{id}")
    public Artiste unArtiste(@PathVariable Long id) {
        return artisteService.findById(id);
    }



    @GetMapping("/concerts")
    public List<Concert> tousLesConcerts() {
        return concertService.findAll();
    }
    @GetMapping("/concerts/{id}")
    public Concert unConcert(@PathVariable Long id) {
        return concertService.findById(id);
    }



    @GetMapping("/scenes")
    public List<Scene> toutesLesScenes() {
        return sceneService.findAll();
    }
    @GetMapping("/scenes/{id}")
    public Scene uneScene(@PathVariable Long id) {
        return sceneService.findById(id);
    }



    @GetMapping("/editions")
    public List<Edition> toutesLesEditions() {
        return editionService.findAll();
    }
}