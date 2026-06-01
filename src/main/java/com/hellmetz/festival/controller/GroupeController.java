package com.hellmetz.festival.controller;

import com.hellmetz.festival.model.Groupe;
import com.hellmetz.festival.service.GroupeService;
import com.hellmetz.festival.service.ArtisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Controller
@RequestMapping("/groupes")
public class GroupeController {

    @Autowired
    private GroupeService groupeService;
    @Autowired
    private ArtisteService artisteService;

    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/Desktop/StageHellMetz/uploads/groupes/";

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
            model.addAttribute("artistes", artisteService.findByGroupeId(id));

        } else {
            model.addAttribute("groupe", new Groupe());
            model.addAttribute("pageTitle", "Nouveau groupe - HellMetz");
            model.addAttribute("artistes", java.util.Collections.emptyList());

        }
        return "groupe/edit";
    }


    @PostMapping("/edit")
    public String save(@ModelAttribute Groupe groupe,
                       @RequestParam(required = false) MultipartFile urlLogoGroupe,
                       @RequestParam(required = false) String supprimerPhoto) {

        if ("true".equals(supprimerPhoto)) {
            groupe.setUrlLogo(null);
        }

        if (urlLogoGroupe != null && !urlLogoGroupe.isEmpty()) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = urlLogoGroupe.getOriginalFilename();
                Path targetLocation = uploadPath.resolve(fileName);
                Files.copy(urlLogoGroupe.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                groupe.setUrlLogo(fileName);

            } catch (IOException e) {
                e.printStackTrace();
                return "groupe/edit";
            }
        }

        groupeService.save(groupe);

        return "redirect:/groupes/liste";
    }

    @GetMapping("/uploads/{nomFichier:.+}")
    public ResponseEntity<Resource> voirPhoto(@PathVariable String nomFichier) throws IOException {
        Path cheminFichier = Paths.get(UPLOAD_DIR).resolve(nomFichier).normalize();
        Resource resource = new UrlResource(cheminFichier.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + nomFichier + "\"")
                .body(resource);
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        groupeService.deleteById(id);
        return "redirect:/groupes/liste";
    }
}