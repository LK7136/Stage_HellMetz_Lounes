package com.hellmetz.festival.controller;

import com.hellmetz.festival.model.Concert;
import com.hellmetz.festival.service.ConcertService;
import com.hellmetz.festival.service.EditionService;
import com.hellmetz.festival.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/concerts")
public class ConcertController {

    @Autowired
    private ConcertService concertService;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private EditionService editionService;

    @GetMapping("/liste")
    public String liste(Model model) {
        model.addAttribute("concerts", concertService.findAll());
        model.addAttribute("pageTitle", "HellMetz - Concerts");
        model.addAttribute("activeMenu", "templates");
        return "concert/list";
    }


    @GetMapping("/ajouter")
    public String edit(@RequestParam(required = false) Long id, Model model)  {

        if (id != null) {
            Concert concert = concertService.findById(id);
            model.addAttribute("concert", concert);
            model.addAttribute("pageTitle", "Modifier le concert - HellMetz");
        } else {
            model.addAttribute("concert", new Concert());
            model.addAttribute("pageTitle", "Nouveau concert - HellMetz");
        }

        model.addAttribute("scenes", sceneService.findAll());
        model.addAttribute("editions", editionService.findAll());

        return "concert/edit";
    }


    @PostMapping("/edit")
    public String save(@ModelAttribute Concert concert) {
        concertService.save(concert);
        return "redirect:/concerts/liste";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        concertService.deleteById(id);
        return "redirect:/concerts/liste";
    }

}
