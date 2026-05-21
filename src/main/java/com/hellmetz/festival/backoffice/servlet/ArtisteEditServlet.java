package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.ArtisteDao;
import com.hellmetz.festival.backoffice.model.Artiste;
import com.hellmetz.festival.backoffice.dao.StyleDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

@MultipartConfig
@WebServlet("/backoffice/artistes/edit")
public class ArtisteEditServlet extends HttpServlet {

    private ArtisteDao artisteDao = new ArtisteDao();
    private StyleDao styleDao = new StyleDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String idGroupeParam = req.getParameter("id_groupe_param");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Artiste artiste = artisteDao.findById(id);

                req.setAttribute("artiste", artiste);
                req.setAttribute("pageTitle", "Modifier l'artiste - HellMetz");
                req.setAttribute("id_groupe", idGroupeParam);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            // Mode Création
            req.setAttribute("pageTitle", "Nouvel artiste - HellMetz");
        }



        req.setAttribute("styles", styleDao.findAll());



        // On indique au layout quelle page charger au centre
        req.setAttribute("contentPage", "/WEB-INF/backoffice/artistes/edit.jsp");

        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/backoffice/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String idGroupeParam = req.getParameter("id_groupe_param");



        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String nomScene = req.getParameter("nom_scene");
        String biographie = req.getParameter("biographie");
        int idStyle = parseInteger(req.getParameter("id_style"), 0);
        String nationalite = req.getParameter("nationalite");
        BigDecimal cachet = parseBigDecimal(req.getParameter("cachet"), BigDecimal.ZERO);
        String urlFacebook = req.getParameter("url_facebook");
        String urlInstagram = req.getParameter("url_instagram");
        String urlSpotify = req.getParameter("url_spotify");
        String exigencesCatering = req.getParameter("exigences_catering");
        String urlPhoto = req.getParameter("url_photo");
        int id_groupe = parseInteger(req.getParameter("id_groupe"), 0);


        Artiste artiste = new Artiste(0, nom, prenom, nomScene, biographie, urlPhoto, idStyle, nationalite, cachet, urlFacebook, urlInstagram, urlSpotify, exigencesCatering, id_groupe);

// Gestion de l'upload de la photo
        Part filePart = req.getPart("urlPhotoArtiste");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("/backoffice/images/artistes/");
            Files.createDirectories(Paths.get(uploadDir));
            filePart.write(uploadDir + fileName);
            urlPhoto = "/backoffice/images/artistes/" + fileName;
        }

        // Gestion de la suppression de la photo
        String supprimerPhoto = req.getParameter("supprimer_photo");
        if ("true".equals(supprimerPhoto)) {
            urlPhoto = null;
        }


        // 3. Logique d'Update ou d'Insert
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                artiste.setId(id);
                artisteDao.update(artiste);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            artisteDao.insert(artiste);
        }

        // 4. Redirection vers la liste
        if (id_groupe != 0) {
            resp.sendRedirect(req.getContextPath() + "/backoffice/groupes/edit?id=" + id_groupe);

        } else {
            resp.sendRedirect(req.getContextPath() + "/backoffice/artistes");
        }

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