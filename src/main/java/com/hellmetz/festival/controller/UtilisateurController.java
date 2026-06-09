package com.hellmetz.festival.controller;

import com.hellmetz.festival.model.Role;
import com.hellmetz.festival.model.Utilisateur;
import com.hellmetz.festival.service.RoleService;
import com.hellmetz.festival.service.UtilisateurDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurDetailService utilisateurDetailService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String success,
                            HttpServletRequest request,
                            Model model) {

        if ("created".equals(success)) {
            model.addAttribute("erreur", "Le compte a été créé avec succès !");
        }

        // chercher l'erreur d'authentification cachée en session par Spring Security
        Object exceptionEnSession = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        if (exceptionEnSession != null) {
            // erreur de saisie (Identifiant ou mot de passe incorrect)
            if (exceptionEnSession instanceof BadCredentialsException) {
                model.addAttribute("typeErreur", "SaisieIncorrecte");
            }
            // la VM ou serveur ne répond pas
            // (Spring lève souvent une InternalAuthenticationServiceException dans ce cas)
            else if (exceptionEnSession instanceof InternalAuthenticationServiceException) {
                model.addAttribute("typeErreur", "ServIndisponible");
            }
            // autre erreur au cas où
            else {
                model.addAttribute("typeErreur", "Erreur");
            }
        }

        return "login";
    }


    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        model.addAttribute("roles", roleService.findAll());
        return "register";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute Utilisateur utilisateur,
                           @RequestParam Long idRole,
                           Model model) {

        Role roleChoisi = roleService.findById(idRole);

        if (roleChoisi == null) {
            model.addAttribute("erreur", "Rôle invalide.");
            model.addAttribute("roles", roleService.findAll());
            return "register";
        }

        utilisateur.setRoles(java.util.List.of(roleChoisi));

        try {
            utilisateurDetailService.creerUtilisateur(utilisateur);
            return "redirect:/login?success=created";
        } catch (Exception e) {
            model.addAttribute("erreur", "Erreur : " + e.getMessage());
            model.addAttribute("roles", roleService.findAll());
            return "register";
        }
    }

}
