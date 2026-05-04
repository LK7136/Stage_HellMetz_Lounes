package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.model.UtilisateurSessionDTO;
import java.util.List;

import com.hellmetz.festival.backoffice.dao.UtilisateurDao;
import com.hellmetz.festival.backoffice.model.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UtilisateurDao utilisateurDao = new UtilisateurDao();

    // Équivalent du case 'demanderConnexion' (Affichage du formulaire)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirige vers la vue JSP
        request.getRequestDispatcher("/WEB-INF/backoffice/login.jsp").forward(request, response);
    }

    // Équivalent du case 'validerConnexion' (Traitement du formulaire)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identifiant = request.getParameter("identifiant");
        String motDePasse = request.getParameter("motDePasse");

        // vérifier si l'utilisateur existe avec ce mot de passe
        Utilisateur utilisateur = utilisateurDao.getUnUtilisateur(identifiant, motDePasse);

        // si l'utilisateur n'existe pas (mot de passe faux ou identifiant inconnu)
        if (utilisateur == null) {
        // positionner le message d'erreur (équivalent de $erreur = ...)
            request.setAttribute("erreur", "Identifiant ou mot de passe incorrect.");

             // inclure la vue correspondant au formulaire
            request.getRequestDispatcher("/WEB-INF/backoffice/login.jsp").forward(request, response);
        } else {
            // Authentification réussie : on récupère les rôles de l'utilisateur
            List<String> codesRoles = utilisateurDao.getCodesRoles(
            utilisateur.getIdUtilisateur());

            // On construit le DTO destiné à la session (sans le mot de passe)
            UtilisateurSessionDTO dto =
                    new UtilisateurSessionDTO(utilisateur, codesRoles);

            // On stocke le DTO en session, PAS l'objet Utilisateur complet
            HttpSession session = request.getSession();
            session.setAttribute("utilisateurConnecte", dto);

            // Redirection vers la page d'accueil sécurisée du backoffice
            response.sendRedirect(request.getContextPath() + "/backoffice/dashboard");
            }

    }
    }


