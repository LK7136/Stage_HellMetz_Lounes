package com.hellmetz.festival.controller;

import com.hellmetz.festival.model.Groupe;
import com.hellmetz.festival.service.GroupeService;
import com.hellmetz.festival.service.ArtisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groupes")
public class GroupeController {

    @Autowired
    private GroupeService groupeService;
    @Autowired
    private ArtisteService artisteService;


    @GetMapping("/liste")
    public String listGroupes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") String taille,
            Model model) {

        boolean tout = "tout".equalsIgnoreCase(taille);
        Page<Groupe> pageGroupes = tout
                ? groupeService.findTout()
                : groupeService.findPage(page, parseTaille(taille));

        model.addAttribute("groupes", pageGroupes.getContent()); // les lignes du tableau
        model.addAttribute("page", pageGroupes);                 // les métadonnées de pagination
        model.addAttribute("taille", taille);                    // pour pré-sélectionner la liste déroulante
        model.addAttribute("pageTitle", "HellMetz - Groupes");
        model.addAttribute("activeMenu", "groupes");             // ⚠️ corrige "templates" -> "groupes"
        return "groupe/list";
    }

    private int parseTaille(String taille) {
        try {
            int t = Integer.parseInt(taille);
            return t > 0 ? t : 10;
        } catch (NumberFormatException e) {
            return 10;
        }
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