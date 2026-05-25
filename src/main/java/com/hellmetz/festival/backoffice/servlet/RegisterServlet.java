package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.UtilisateurDao;
import com.hellmetz.festival.backoffice.dao.RoleDao;
import com.hellmetz.festival.backoffice.dao.permissionDao;
import com.hellmetz.festival.backoffice.model.permission;
import com.hellmetz.festival.backoffice.model.role;
import com.hellmetz.festival.model.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UtilisateurDao utilisateurDao = new UtilisateurDao();
    private RoleDao roleDao = new RoleDao();
    private permissionDao permissionDao = new permissionDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<role> roles = roleDao.getTousLesRoles();
            List<permission> permissions = permissionDao.getToutesLesPermissions();

            // Construire le JSON des permissions par rôle
            StringBuilder json = new StringBuilder("{");
            for (int i = 0; i < roles.size(); i++) {
                role r = roles.get(i);
                List<Long> perms = roleDao.getPermissionsParRole(r.getIdRole());
                json.append("\"").append(r.getIdRole()).append("\":[");
                json.append(perms.stream().map(String::valueOf).collect(Collectors.joining(",")));
                json.append("]");
                if (i < roles.size() - 1) json.append(",");
            }
            json.append("}");

            request.setAttribute("roles", roles);
            request.setAttribute("permissions", permissions);
            request.setAttribute("permissionsParRole", json.toString());

            request.getRequestDispatcher("/WEB-INF/backoffice/register.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Erreur lors du chargement des données.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom         = request.getParameter("nom");
        String prenom      = request.getParameter("prenom");
        String email       = request.getParameter("email");
        String identifiant = request.getParameter("identifiant");
        String motDePasse  = request.getParameter("motDePasse");
        String idRoleStr   = request.getParameter("idRole");

        if (idRoleStr == null || idRoleStr.isEmpty()) {
            request.setAttribute("erreur", "Veuillez choisir un rôle.");
            doGet(request, response);
            return;
        }

        long idRole = Long.parseLong(idRoleStr);
        role roleChoisi = roleDao.getUnRole(idRole);

        if (roleChoisi == null) {
            request.setAttribute("erreur", "Rôle invalide.");
            doGet(request, response);
            return;
        }

        // Récupération des permissions depuis la BDD pour ce rôle
        List<Long> idsPermissions = roleDao.getPermissionsParRole(idRole);

        // Hachage du mot de passe
        String motDePasseHache = utilisateurDao.hacherMotDePasse(motDePasse);

        Utilisateur u = new Utilisateur();
        u.setNom(nom);
        u.setPrenom(prenom);
        u.setEmail(email);
        u.setIdentifiant(identifiant);
        u.setMotDePasse(motDePasseHache);
        u.setActif(true);

        try {
            long idUtilisateur = utilisateurDao.creerUtilisateur(u);
            utilisateurDao.associerRole(idUtilisateur, idRole);

            response.sendRedirect(request.getContextPath() + "/login?success=created");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erreur", "Erreur lors de la création : " + e.getMessage());
            doGet(request, response);
        }
    }
}