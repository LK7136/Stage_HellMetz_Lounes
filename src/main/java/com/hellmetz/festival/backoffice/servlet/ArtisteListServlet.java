package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.ArtisteDao;
import com.hellmetz.festival.backoffice.model.Artiste;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/backoffice/artistes")
public class ArtisteListServlet extends HttpServlet {

    private ArtisteDao artisteDao= new ArtisteDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Artiste> artistes = artisteDao.findAll();
        req.setAttribute("artistes", artistes);

        // Pour le layout
        req.setAttribute("pageTitle", "HellMetz - Artistes");
        req.setAttribute("activeMenu", "artistes");  // pour surligner le menu
        req.setAttribute("contentPage", "/WEB-INF/backoffice/artistes/list.jsp");


        req.getRequestDispatcher("/WEB-INF/backoffice/layout.jsp").forward(req, resp);
    }
}
