//package com.hellmetz.festival.backoffice.servlet;
//
//import com.hellmetz.festival.backoffice.dao.SceneDao;
//import com.hellmetz.festival.model.Scene;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/backoffice/scenes/delete")
//public class SceneDeleteServlet extends HttpServlet {
//
//    private SceneDao sceneDao = new SceneDao();
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
//            Scene sceneASupprimer = new Scene();
//            sceneASupprimer.setId(id);
//
//            // 3. Appel de la méthode delete du DAO
//            sceneDao.delete(sceneASupprimer);
//        }
//
//        // 4. Redirection vers la liste des scènes
//        response.sendRedirect(request.getContextPath() + "/backoffice/scenes");
//    }
//}