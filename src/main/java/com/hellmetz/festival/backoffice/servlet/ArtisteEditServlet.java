package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.ArtisteDao;
import com.hellmetz.festival.backoffice.model.Artiste;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/backoffice/artiste/edit")
public class ArtisteEditServlet extends HttpServlet {

    private ArtisteDao artisteDao = new ArtisteDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            // Mode Modification : on charge l'artiste existant
            int id = Integer.parseInt(idParam);
            Artiste artiste = artisteDao.findById(id);
            req.setAttribute("artiste", artiste);
            req.setAttribute("pageTitle", "Modifier l'artiste");
        } else {
            // Mode Création
            req.setAttribute("pageTitle", "Nouvel artiste");
        }

        req.setAttribute("contentPage", "artiste/edit.jsp");
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/backoffice/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Récupération des données épurées
        String idParam = req.getParameter("id");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String nomScene = req.getParameter("nom_scene");
        String biographie = req.getParameter("biographie");
        String urlPhoto = req.getParameter("url_photo");
        int idStyle = parseInteger(req.getParameter("id_style"), 0);
        String nationalite = req.getParameter("nationalite");
        BigDecimal cachet = parseBigDecimal(req.getParameter("cachet"), BigDecimal.ZERO);
        String urlFacebook = req.getParameter("url_facebook");
        String urlInstagram = req.getParameter("url_instagram");
        String urlSpotify = req.getParameter("url_spotify");
        String exigencesCatering = req.getParameter("exigences_catering");

        // 2. Création de l'objet Artiste avec les bonnes propriétés
        Artiste artiste = new Artiste();
        artiste.setNom(nom);
        artiste.setPrenom(prenom);
        artiste.setNom_scene(nomScene);
        artiste.setBiographie(biographie);
        artiste.setUrl_photo(urlPhoto);
        artiste.setId_style(idStyle);
        artiste.setNationalite(nationalite);
        artiste.setCachet(cachet);
        artiste.setUrl_facebook(urlFacebook);
        artiste.setUrl_instagram(urlInstagram);
        artiste.setUrl_spotify(urlSpotify);
        artiste.setExigences_catering(exigencesCatering);

        // 3. Logique d'Update ou d'Insert
        if (idParam != null && !idParam.trim().isEmpty()) {
            int id = Integer.parseInt(idParam);
            artiste.setId(id);
            artisteDao.update(artiste);
        } else {
            artisteDao.insert(artiste);
        }

        // 4. Redirection vers la liste
        resp.sendRedirect(req.getContextPath() + "/backoffice/artiste");
    }

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

    private BigDecimal parseBigDecimal(String value, BigDecimal defaultValue) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return new BigDecimal(value.replace(",", "."));
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}