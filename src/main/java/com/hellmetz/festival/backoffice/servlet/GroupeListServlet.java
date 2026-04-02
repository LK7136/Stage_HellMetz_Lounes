package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.GroupeDao;
import com.hellmetz.festival.backoffice.model.Groupe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/backoffice/groupes")
public class GroupeListServlet extends HttpServlet {

    private GroupeDao groupeDao = new GroupeDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Groupe> groupes = groupeDao.findAll();
        req.setAttribute("groupes", groupes);

        // Pour le layout
        req.setAttribute("pageTitle", "HellMetz - Groupes");
        req.setAttribute("activeMenu", "groupes");  // pour surligner le menu
        req.setAttribute("contentPage", "/WEB-INF/backoffice/groupes/list.jsp");

        req.getRequestDispatcher("/WEB-INF/backoffice/layout.jsp").forward(req, resp);
    }
}
