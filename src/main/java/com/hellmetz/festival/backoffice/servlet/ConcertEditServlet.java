package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.ConcertDao;
import com.hellmetz.festival.backoffice.model.Concert;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/backoffice/concerts/edit")
public class ConcertEditServlet extends HttpServlet {

    private ConcertDao concertDao = new ConcertDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Concert concert = concertDao.findById(id);
                req.setAttribute("concert", concert);
                req.setAttribute("pageTitle", "Modifier le concert - HellMetz");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("pageTitle", "Nouveau concert - HellMetz");
        }

        req.setAttribute("activeMenu", "concerts");
        req.setAttribute("contentPage", "/WEB-INF/backoffice/concerts/edit.jsp");

        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/backoffice/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        int idScene     = parseInteger(req.getParameter("id_scene"), 0);
        int idEdition   = parseInteger(req.getParameter("id_edition"), 0);
        String statut   = req.getParameter("statut") != null ? req.getParameter("statut") : "Non programmé"; // ✅ String au lieu de boolean
        int decibelsMax = parseInteger(req.getParameter("decibels_max"), 95);

        Date dateHeureDebut   = parseDate(req.getParameter("date_heure_debut"));
        Date dateHeureFin     = parseDate(req.getParameter("date_heure_fin"));
        Date dateBalanceDebut = parseDate(req.getParameter("date_balance_debut"));
        Date dateBalanceFin   = parseDate(req.getParameter("date_balance_fin"));

        Concert concert = new Concert(
                idScene, idEdition,
                0,
                statut, // ✅ String
                dateHeureDebut, dateHeureFin,
                dateBalanceDebut, dateBalanceFin,
                decibelsMax
        );

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                concert.setId_concert(id);
                concertDao.update(concert);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            concertDao.insert(concert);
        }

        resp.sendRedirect(req.getContextPath() + "/backoffice/concerts");
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

    private Date parseDate(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        String[] formats = {"yyyy-MM-dd'T'HH:mm", "yyyy-MM-dd"};
        for (String format : formats) {
            try {
                return new SimpleDateFormat(format).parse(value);
            } catch (ParseException ignored) {}
        }
        return null;
    }
}