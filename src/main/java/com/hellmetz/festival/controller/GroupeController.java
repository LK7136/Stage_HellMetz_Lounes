package com.hellmetz.festival.controller;

import com.hellmetz.festival.model.Groupe;
import com.hellmetz.festival.service.GroupeService;
import com.hellmetz.festival.service.ArtisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/groupes")
public class GroupeController {

    @Autowired
    private GroupeService groupeService;
    @Autowired
    private ArtisteService artisteService;


    @GetMapping("/liste")
    public String listGroupes(Model model) {
        List<Groupe> groupes = groupeService.findAll();

        model.addAttribute("groupes", groupes);
        model.addAttribute("pageTitle", "HellMetz - Groupes");
        model.addAttribute("activeMenu", "groupes");
        model.addAttribute("contentPage", "/WEB-INF/backoffice/groupes/list.jsp");

        return "backoffice/layout"; // Redirige vers la vue du layout principal
    }


    @GetMapping("/ajouter")
    public String edit(@RequestParam(required = false) Long id, Model model) {

        if (id != null) {
            Groupe groupe = groupeService.findById(id);
            model.addAttribute("groupe", groupe);
            model.addAttribute("pageTitle", "Modifier le groupe - HellMetz");
        } else {
            model.addAttribute("groupe", new Groupe());
            model.addAttribute("pageTitle", "Nouveau groupe - HellMetz");
        }

        model.addAttribute("activeMenu", "groupes");
        model.addAttribute("contentPage", "/WEB-INF/backoffice/groupes/edit.jsp");

        return "groupe/edit";
    }


    @PostMapping("/edit")
    public String save(@ModelAttribute Groupe groupe) {

        groupeService.save(groupe);

        return "redirect:/groupes/liste";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        groupeService.deleteById(id);
        return "redirect:/groupes/liste";
    }
}