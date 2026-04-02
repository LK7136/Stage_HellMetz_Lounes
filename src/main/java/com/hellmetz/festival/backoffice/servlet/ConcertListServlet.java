package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.ConcertDao;
import com.hellmetz.festival.backoffice.model.Concert; // ✅ Import Concert, pas Groupe
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/backoffice/concerts")
public class ConcertListServlet extends HttpServlet {

    private ConcertDao concertDao = new ConcertDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Concert> concerts = concertDao.findAll();
        req.setAttribute("concerts", concerts);

        req.setAttribute("pageTitle", "HellMetz - Concert");
        req.setAttribute("activeMenu", "concerts");
        req.setAttribute("contentPage", "/WEB-INF/backoffice/concerts/list.jsp");

        req.getRequestDispatcher("/WEB-INF/backoffice/layout.jsp").forward(req, resp);
    }
}