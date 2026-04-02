package com.hellmetz.festival.backoffice.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


// Ce filtre s'applique à toutes les URL commençant par /backoffice/
@WebFilter("/backoffice/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false); // false: ne crée pas de session si elle n'existe pas

        // Vérifie si l'attribut 'utilisateurConnecte' existe dans la session
        boolean estConnecte = (session != null && session.getAttribute("utilisateurConnecte") != null);

        // On récupère le chemin demandé pour éviter une boucle infinie si on demande déjà le login
        String loginURI = httpRequest.getContextPath() + "/login";

        boolean demandeLogin = httpRequest.getRequestURI().equals(loginURI);

        // Si l'utilisateur est connecté, ou s'il essaie d'accéder à la page de login, on le laisse passer
        if (estConnecte || demandeLogin) {
            chain.doFilter(request, response); // Laisse passer la requête (l'équivalent du 'else' en PHP)
        } else {
            // Sinon, on le redirige vers le contrôleur de connexion (l'équivalent du require 'c_connexion.php')
            httpResponse.sendRedirect(loginURI);
        }
    }
}
