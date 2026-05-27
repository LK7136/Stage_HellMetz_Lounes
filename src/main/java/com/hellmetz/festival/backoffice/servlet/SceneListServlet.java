//package com.hellmetz.festival.backoffice.servlet;
//
//import com.hellmetz.festival.backoffice.dao.SceneDao;
//import com.hellmetz.festival.model.Scene;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/backoffice/scenes")
//public class SceneListServlet extends HttpServlet {
//
//    private final SceneDao sceneDao = new SceneDao();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        List<Scene> scenes = sceneDao.findAll();
//        req.setAttribute("scenes", scenes);
//
//        // Layout
//        req.setAttribute("pageTitle", "HellMetz - Scènes");
//        req.setAttribute("activeMenu", "scenes");
//        req.setAttribute("contentPage", "/WEB-INF/backoffice/scenes/list.jsp");
//
//        req.getRequestDispatcher("/WEB-INF/backoffice/layout.jsp").forward(req, resp);
//    }
//}