package com.hellmetz.festival.controller;

import com.hellmetz.festival.model.Scene;
import com.hellmetz.festival.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/scenes")
public class SceneController {
    @Autowired
    private SceneService sceneService;

    @GetMapping("/liste")
    public String liste(Model model) {
        model.addAttribute("scenes", sceneService.findAll());
        model.addAttribute("pageTitle", "HellMetz - Scenes");
        model.addAttribute("activeMenu", "scenes");
        return "scene/list";
    }


    @GetMapping("/ajouter")
    public String edit(@RequestParam(required = false) Long id, Model model)  {

        if (id != null) {
            Scene concert = sceneService.findById(id);
            model.addAttribute("scene", concert);
            model.addAttribute("pageTitle", "Modifier la scene - HellMetz");
        } else {
            model.addAttribute("scene", new Scene());
            model.addAttribute("pageTitle", "Nouvel scene - HellMetz");
        }

        model.addAttribute("scenes", sceneService.findAll());
        model.addAttribute("activeMenu", "scenes");

        return "scene/edit";
    }


    @PostMapping("/edit")
    public String save(@ModelAttribute Scene scene) {
        sceneService.save(scene);
        return "redirect:/scenes/liste";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        sceneService.deleteById(id);
        return "redirect:/scenes/liste";
    }

}
