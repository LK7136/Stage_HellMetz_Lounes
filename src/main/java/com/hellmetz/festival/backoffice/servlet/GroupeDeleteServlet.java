//package com.hellmetz.festival.backoffice.servlet;
//
//import com.hellmetz.festival.backoffice.dao.GroupeDao;
//import com.hellmetz.festival.model.Groupe;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/backoffice/groupes/delete")
//public class GroupeDeleteServlet extends HttpServlet {
//
//    private GroupeDao groupeDao = new GroupeDao();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String idParam = request.getParameter("id");
//
//        if (idParam != null) {
//            int id = Integer.parseInt(idParam);
//
//
//            Groupe groupeASupprimer = new Groupe();
//            groupeASupprimer.setId(id);
//
//            // 3. Appel de la méthode delete du DAO
//            groupeDao.delete(groupeASupprimer);
//        }
//
//        // 4. Redirection vers la liste des scènes
//        response.sendRedirect(request.getContextPath() + "/backoffice/groupes");
//    }
//}