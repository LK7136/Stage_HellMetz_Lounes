package com.hellmetz.festival.controller;

import com.hellmetz.festival.model.Artiste;
import com.hellmetz.festival.model.Style;
import com.hellmetz.festival.service.ArtisteService;
import com.hellmetz.festival.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller                       // Déclare ce Bean comme Controller Spring MVC
@RequestMapping("/artistes")       // Préfixe commun à toutes les routes
public class ArtisteController {

    @Autowired
    private ArtisteService artisteService;  // Injecté par Spring
    @Autowired
    private StyleService styleService;  // Injecté par Spring


    // Affichage de la liste — équivaut à votre doGet() actuel
    @GetMapping("/liste")
    public String liste(Model model) {
        model.addAttribute("artistes", artisteService.findAll());
        model.addAttribute("pageTitle", "HellMetz - Artistes");
        model.addAttribute("activeMenu", "templates");
        return "artiste/list";
    }

    // Formulaire d'edit — GET affiche le form vide
    @GetMapping("/ajouter")
    public String edit(@RequestParam(required = false) Long id,
                       @RequestParam(required = false) Integer idGroupeParam,
                       Model model)  {

        // si id null on modifie l'artiste
        if (id != null) {
            Artiste artiste = artisteService.findById(id);
            model.addAttribute("artiste", artiste);
            model.addAttribute("pageTitle", "Modifier l'artiste - HellMetz");
        } else { // sinon on le creer
            model.addAttribute("artiste", new Artiste());
            model.addAttribute("pageTitle", "Nouvel artiste - HellMetz");
        }

        // si on modifie un artiste dans la page groupe on garde son id
        // pour retourner d'ou on vient (ptet a changer car retourner la
        // liste des artistes)

        if (idGroupeParam != null) {
            model.addAttribute("idGroupeParam", idGroupeParam);
        }

        // pour le menu deroulant des styles
        model.addAttribute("styles", styleService.findAll());
        return "/artiste/edit";
    }




    // Formulaire d'edit à enregistrer et envoyer
    @PostMapping("/edit")
    public String save(@ModelAttribute Artiste artiste,
                       @RequestParam(required = false) Integer idGroupeParam,
                       @RequestParam(required = false) MultipartFile urlPhotoArtiste,
                       @RequestParam(required = false) String supprimerPhoto) {

        // pour supprime photo
        if ("true".equals(supprimerPhoto)) {
            artiste.setUrlPhoto(null);
        }

        // pour upload photo
        if (urlPhotoArtiste != null && !urlPhotoArtiste.isEmpty()) {
            String fileName = urlPhotoArtiste.getOriginalFilename();
            artiste.setUrlPhoto("/static/images/artistes/" + fileName);
        }

        artisteService.save(artiste);

        // redirge
        if (idGroupeParam != null) {
            return "redirect:/groupe/edit?id=" + idGroupeParam;
        }
        return "redirect:/artistes/liste";
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        artisteService.deleteById(id);
        return "redirect:/artistes/liste";
    }

}
