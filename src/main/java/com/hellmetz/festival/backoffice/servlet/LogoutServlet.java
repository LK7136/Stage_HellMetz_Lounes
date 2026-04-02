package com.hellmetz.festival.backoffice.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Récupérer la session courante (false = ne pas en créer une nouvelle si elle n'existe pas)
        HttpSession session = request.getSession(false);

        // 2. Si une session existe, on la détruit
        if (session != null) {
            // invalidate() supprime toutes les données de la session, y compris "utilisateurConnecte"
            session.invalidate();
        }

        // 3. Rediriger l'utilisateur vers la page de connexion (avec un petit paramètre pour afficher un message)
        response.sendRedirect(request.getContextPath() + "/login?logout=success");
    }
}

