package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.SceneDao;
import com.hellmetz.festival.model.Scene;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@MultipartConfig
@WebServlet("/backoffice/scenes/edit")
public class SceneEditServlet extends HttpServlet {

    private final SceneDao sceneDao = new SceneDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Scene scene = sceneDao.findById(id);
                if (scene != null) {
                    req.setAttribute("scene", scene);
                }
                req.setAttribute("pageTitle", "Modifier la scène - HellMetz");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                req.setAttribute("pageTitle", "Nouvelle scène - HellMetz");
            }
        } else {
            req.setAttribute("pageTitle", "Nouvelle scène - HellMetz");
        }

        req.setAttribute("contentPage", "/WEB-INF/backoffice/scenes/edit.jsp");

        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/backoffice/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idParam = req.getParameter("id");

        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        int capacite = parseInteger(req.getParameter("capacite"), 0);
        boolean actif = req.getParameter("actif") != null;
        String type = req.getParameter("type");
        int superficie = parseInteger(req.getParameter("superficie"), 0);

        // On conserve l'URL existante par défaut (champ hidden du formulaire)
        String urlPlanTechnique = req.getParameter("urlPlanTechnique");

        // Gestion de l'upload du fichier PDF
        Part filePart = req.getPart("file_plan");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("/backoffice/documents/plans/");
            Files.createDirectories(Paths.get(uploadDir));
            filePart.write(uploadDir + fileName);
            urlPlanTechnique = "/backoffice/documents/plans/" + fileName;
        }

        Scene scene = new Scene(0, nom, description, capacite, actif, type, superficie, urlPlanTechnique);

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                scene.setId(id);
                sceneDao.update(scene);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            sceneDao.insert(scene);
        }

        resp.sendRedirect(req.getContextPath() + "/backoffice/scenes");
    }

    private int parseInteger(String value, int defaultValue) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}