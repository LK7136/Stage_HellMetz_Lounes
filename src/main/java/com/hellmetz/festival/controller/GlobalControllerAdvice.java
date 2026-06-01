package com.hellmetz.festival.controller;

import com.hellmetz.festival.model.Utilisateur;
import com.hellmetz.festival.security.UtilisateurDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Rend la variable {@code utilisateurConnecte} (entite metier Utilisateur)
 * disponible automatiquement dans tous les templates Thymeleaf.
 * Aucune requete BDD : l'utilisateur vit deja en session via Spring Security.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("utilisateurConnecte")
    public Utilisateur utilisateurConnecte() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }
        if (auth.getPrincipal() instanceof UtilisateurDetails ud) {
            return ud.getUtilisateur();
        }
        return null;
    }
}
