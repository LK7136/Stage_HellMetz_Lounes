package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.UtilisateurDao;
import com.hellmetz.festival.backoffice.dao.RoleDao; // Changé de roleDao en RoleDao
import com.hellmetz.festival.backoffice.dao.permissionDao;
import com.hellmetz.festival.backoffice.model.permission;
import com.hellmetz.festival.backoffice.model.role;
import com.hellmetz.festival.backoffice.model.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    // Initialisation des DAOs
    private UtilisateurDao utilisateurDao = new UtilisateurDao();
    private RoleDao        roleDao        = new RoleDao();
    private permissionDao  permissionDao  = new permissionDao();

    /**
     * Affiche le formulaire d'inscription avec les rôles et permissions
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Chargement des données pour alimenter le formulaire (listes déroulantes/checkbox)
            List<role> roles = roleDao.getTousLesRoles();
            List<permission> permissions = permissionDao.getToutesLesPermissions();

            request.setAttribute("roles", roles);
            request.setAttribute("permissions", permissions);

            // Redirection vers la page JSP
            request.getRequestDispatcher("/WEB-INF/backoffice/register.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors du chargement des données.");
        }
    }

    /**
     * Traite l'inscription de l'utilisateur et ses habilitations
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Récupération des paramètres du formulaire
        String nom         = request.getParameter("nom");
        String prenom      = request.getParameter("prenom");
        String email       = request.getParameter("email");
        String identifiant = request.getParameter("identifiant");
        String motDePasse  = request.getParameter("motDePasse");
        String idRoleStr   = request.getParameter("idRole");

        // Validation basique
        if (idRoleStr == null || idRoleStr.isEmpty()) {
            response.sendRedirect("register?error=missing_role");
            return;
        }

        long idRole = Long.parseLong(idRoleStr);

        // 2. Récupération des détails du rôle pour appliquer la règle métier
        role roleChoisi = roleDao.getUnRole(idRole);

        if (roleChoisi == null) {
            response.sendRedirect("register?error=invalid_role");
            return;
        }

        // 3. Détermination de la liste des permissions
        List<Long> idsPermissions = new ArrayList<>();

        // REGLE METIER :
        // Si ADMIN : on récupère TOUTES les permissions de la base
        if ("ADMIN".equals(roleChoisi.getCodeRole())) {
            List<permission> toutes = permissionDao.getToutesLesPermissions();
            for (permission p : toutes) {
                idsPermissions.add(p.getIdPermission());
            }
        }
        // Sinon : on récupère uniquement les IDs des cases cochées
        else {
            String[] valeursCochees = request.getParameterValues("idsPermissions");
            if (valeursCochees != null) {
                for (String v : valeursCochees) {
                    idsPermissions.add(Long.parseLong(v));
                }
            }
        }

        // 4. Préparation de l'objet Utilisateur
        // Hachage du mot de passe (via la méthode du DAO de la mission 2)
        String motDePasseHache = utilisateurDao.hacherMotDePasse(motDePasse);

        Utilisateur u = new Utilisateur();
        u.setNom(nom);
        u.setPrenom(prenom);
        u.setEmail(email);
        u.setIdentifiant(identifiant);
        u.setMotDePasse(motDePasseHache);
        u.setActif(true);

        // 5. Enregistrement en base de données
        try {
            // A. Création de l'utilisateur (retourne l'ID généré)
            long idUtilisateur = utilisateurDao.creerUtilisateur(u);

            // B. Association dans la table de liaison 'role_utilisateur'
            utilisateurDao.associerRole(idUtilisateur, idRole);

            // C. Association dans la table 'role_permission'
            // Note : Ici on lie les permissions au Rôle comme dans votre DAO
            roleDao.associerPermissions(idRole, idsPermissions);

            // 6. Succès : Redirection vers une page de confirmation ou liste
            response.sendRedirect(request.getContextPath() + "/utilisateurs?success=created");

        } catch (Exception e) {
            e.printStackTrace();
            // En cas d'erreur, on peut renvoyer au formulaire avec un message
            request.setAttribute("errorMessage", "Erreur lors de la création : " + e.getMessage());
            doGet(request, response);
        }
    }
}