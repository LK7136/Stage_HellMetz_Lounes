package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.GroupeDao;
import com.hellmetz.festival.backoffice.model.Groupe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/backoffice/groupes/edit")
public class GroupeEditServlet extends HttpServlet {

    private GroupeDao groupeDao = new GroupeDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        // Si un ID est fourni, on charge les données (Mode Modification)
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Groupe groupe = groupeDao.findById(id);
                req.setAttribute("groupe", groupe);
                req.setAttribute("pageTitle", "Modifier le groupe - HellMetz");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            // Mode Création
            req.setAttribute("pageTitle", "Nouveau groupe - HellMetz");
        }

        // On indique au layout quelle page charger au centre
        req.setAttribute("contentPage", "groupes/edit.jsp");

        // On transfère la requête au layout principal (qui s'occupera d'inclure le header, le menu et la page d'édition)
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/backoffice/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Récupération de l'ID (s'il existe, c'est un UPDATE, sinon c'est un INSERT)
        String idParam = req.getParameter("id");

        // 2. Récupération de toutes les autres données du formulaire
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");

        // Gestion de la case à cocher "actif" (si non cochée, paramètre null)
        boolean actif = req.getParameter("actif") != null;

        // Gestion sécurisée des champs numériques (au cas où ils seraient vides)
        int idConcert = parseInteger(req.getParameter("id_concert"), 0);
        int anneeCreation = parseInteger(req.getParameter("annee_creation"), 0);

        String villeOrigine = req.getParameter("ville_origine");
        String paysOrigine = req.getParameter("pays_origine");
        String urlLogo = req.getParameter("url_logo");
        String siteWeb = req.getParameter("site_web");
        String urlFacebook = req.getParameter("url_facebook");
        String urlInstagram = req.getParameter("url_instagram");
        String urlYoutube = req.getParameter("url_youtube");
        String urlSpotify = req.getParameter("url_spotify");
        String emailContact = req.getParameter("email_contact");
        String telephoneContact = req.getParameter("telephone_contact");
        String urlFicheTechnique = req.getParameter("url_fiche_technique");

        // 3. Création de l'objet Groupe
        Groupe groupe = new Groupe(
                0, // L'ID sera mis à jour juste après si c'est une modification
                nom, description, actif, idConcert, anneeCreation,
                villeOrigine, paysOrigine, urlLogo, siteWeb,
                urlFacebook, urlInstagram, urlYoutube, urlSpotify,
                emailContact, telephoneContact, urlFicheTechnique
        );

        // 4. Appel au DAO selon le mode (Création ou Modification)
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                groupe.setId(id); // On assigne le véritable ID
                groupeDao.update(groupe); // Met à jour en base de données
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            groupeDao.insert(groupe); // Insère un nouveau groupe en base
        }

        // 5. Redirection vers la liste des groupes (Pattern POST-Redirect-GET) pour éviter la double soumission du formulaire
        // Note : Il faudra s'assurer que GroupeListServlet écoute bien sur "/backoffice/groupes/list" ou "/backoffice/groupes"
        resp.sendRedirect(req.getContextPath() + "/backoffice/groupes");
    }

    /**
     * Méthode utilitaire pour parser un String en int avec une valeur par défaut
     */
    private int parseInteger(String value, int defaultValue) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

}