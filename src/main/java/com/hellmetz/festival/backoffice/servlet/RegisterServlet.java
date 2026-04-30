package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.UtilisateurDao;
import com.hellmetz.festival.backoffice.dao.RoleDao;
import com.hellmetz.festival.backoffice.dao.PermissionDao;
import com.hellmetz.festival.backoffice.model.Permission;
import com.hellmetz.festival.backoffice.model.Role;
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

    private UtilisateurDao utilisateurDao = new UtilisateurDao();
    private RoleDao        roleDao        = new RoleDao();
    private PermissionDao  permissionDao  = new PermissionDao();

    // Affichage du formulaire : on charge la liste des rôles et des permissions
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Role>       roles       = roleDao.getTousLesRoles();
        List<Permission> permissions = permissionDao.getToutesLesPermissions();

        request.setAttribute("roles", roles);
        request.setAttribute("permissions", permissions);

        request.getRequestDispatcher("/WEB-INF/backoffice/register.jsp")
                .forward(request, response);
    }

    // Traitement du formulaire
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Récupération des données du formulaire
        String nom         = request.getParameter("nom");
        String prenom      = request.getParameter("prenom");
        String email       = request.getParameter("email");
        String identifiant = request.getParameter("identifiant");
        String motDePasse  = request.getParameter("motDePasse");
        long   idRole      = Long.parseLong(request.getParameter("idRole"));

        // 2. Récupération du rôle choisi
        Role roleChoisi = roleDao.getUnRole(idRole);

        // 3. Détermination de la liste des permissions à associer
        //    Règle métier : si le rôle est ADMIN, toutes les permissions sont accordées,
        //    sinon on prend uniquement celles cochées dans le formulaire.
        List<Long> idsPermissions = new ArrayList<>();

        if ("ADMIN".equals(roleChoisi.getCodeRole())) {
            // Toutes les permissions de la base
            List<Permission> toutes = permissionDao.getToutesLesPermissions();
            for (Permission p : toutes) {
                idsPermissions.add(p.getIdPermission());
            }
        } else {
            // Uniquement celles cochées par l'utilisateur
            String[] valeurs = request.getParameterValues("idsPermissions");
            if (valeurs != null) {
                for (String v : valeurs) {
                    idsPermissions.add(Long.parseLong(v));
                }
            }
        }

        // 4. Hachage du mot de passe (SHA-512 utilisé dans la mission 2)
        String motDePasseHache = utilisateurDao.hacherMotDePasse(motDePasse);

        // 5. Création de l'objet Utilisateur
        Utilisateur u = new Utilisateur();
        u.setNom(nom);
        u.setPrenom(prenom);
        u.setEmail(email);
        u.setIdentifiant(identifiant);
        u.setMotDePasse(motDePasseHache);
        u.setActif(true);

        // 6. Insertion en base + association rôle + association permissions au rôle
        try {
            long idUtilisateur = utilisateurDao.creerUtilisateur(u);
            // table de liaison role_utilisateur
            utilisateurDao.associerRole(idUtilisateur, idRole);
            // table de liaison role_permission (les permissions sont liées au rôle)
            roleDao.associerPermissions(idRole, idsPermissions);

            // Succès : on retourne sur la