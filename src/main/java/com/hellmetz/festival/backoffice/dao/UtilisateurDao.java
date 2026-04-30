package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDao {

    /**
     * Authentifie un utilisateur, vérifie son mot de passe avec jBCrypt,
     * et charge ses rôles et permissions s'il est valide.
     */
    public Utilisateur getUnUtilisateur(String identifiant, String motDePasseClair) {
        Utilisateur utilisateur = null;

        String queryUser = "SELECT id_utilisateur, email, identifiant, mot_de_passe, prenom, nom, actif, date_creation, derniere_connexion " +
                "FROM utilisateur WHERE identifiant = ? AND actif = true";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmtUser = conn.prepareStatement(queryUser)) {

            pstmtUser.setString(1, identifiant);

            try (ResultSet rsUser = pstmtUser.executeQuery()) {
                if (rsUser.next()) {
                    String hashEnBase = rsUser.getString("mot_de_passe");

                    if (BCrypt.checkpw(motDePasseClair, hashEnBase)) {
                        utilisateur = new Utilisateur();
                        utilisateur.setIdUtilisateur(rsUser.getInt("id_utilisateur"));
                        utilisateur.setEmail(rsUser.getString("email"));
                        utilisateur.setIdentifiant(rsUser.getString("identifiant"));
                        utilisateur.setPrenom(rsUser.getString("prenom"));
                        utilisateur.setNom(rsUser.getString("nom"));
                        utilisateur.setActif(rsUser.getBoolean("actif"));
                        utilisateur.setDate_creation(rsUser.getDate("date_creation"));
                        utilisateur.setDernier_connexion(rsUser.getDate("derniere_connexion"));

                        chargerRoles(conn, utilisateur);
                        chargerPermissions(conn, utilisateur);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'authentification : " + e.getMessage());
            e.printStackTrace();
        }
        return utilisateur;
    }

    /**
     * Recherche un utilisateur par son ID (pour le mode Modification)
     */
    public Utilisateur findById(int id) {
        String query = "SELECT id_utilisateur, email, identifiant, nom, prenom, actif, date_creation, derniere_connexion " +
                "FROM utilisateur WHERE id_utilisateur = ?";
        Utilisateur utilisateur = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    utilisateur = new Utilisateur();
                    utilisateur.setIdUtilisateur(rs.getInt("id_utilisateur"));
                    utilisateur.setEmail(rs.getString("email"));
                    utilisateur.setIdentifiant(rs.getString("identifiant"));
                    utilisateur.setNom(rs.getString("nom"));
                    utilisateur.setPrenom(rs.getString("prenom"));
                    utilisateur.setActif(rs.getBoolean("actif"));
                    utilisateur.setDate_creation(rs.getDate("date_creation"));
                    utilisateur.setDernier_connexion(rs.getDate("derniere_connexion"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL findById : " + e.getMessage());
            e.printStackTrace();
        }

        return utilisateur;
    }

    /**
     * Insère un nouvel utilisateur avec son mot de passe haché via BCrypt
     */
    public void insert(Utilisateur utilisateur, String motDePasseClair) {
        String query = "INSERT INTO utilisateur (email, identifiant, mot_de_passe, nom, prenom, actif, date_creation) " +
                "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        String hash = BCrypt.hashpw(motDePasseClair, BCrypt.gensalt());

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, utilisateur.getEmail());
            pstmt.setString(2, utilisateur.getIdentifiant());
            pstmt.setString(3, hash);
            pstmt.setString(4, utilisateur.getNom());
            pstmt.setString(5, utilisateur.getPrenom());
            pstmt.setBoolean(6, utilisateur.getActif());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'insertion : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Met à jour un utilisateur existant.
     * Si motDePasseClair est null ou vide, le mot de passe n'est pas modifié.
     */
    public void update(Utilisateur utilisateur, String motDePasseClair) {
        String query;

        if (motDePasseClair != null && !motDePasseClair.trim().isEmpty()) {
            query = "UPDATE utilisateur SET email=?, identifiant=?, mot_de_passe=?, nom=?, prenom=?, actif=? " +
                    "WHERE id_utilisateur=?";
        } else {
            query = "UPDATE utilisateur SET email=?, identifiant=?, nom=?, prenom=?, actif=? " +
                    "WHERE id_utilisateur=?";
        }

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            if (motDePasseClair != null && !motDePasseClair.trim().isEmpty()) {
                String hash = BCrypt.hashpw(motDePasseClair, BCrypt.gensalt());
                pstmt.setString(1, utilisateur.getEmail());
                pstmt.setString(2, utilisateur.getIdentifiant());
                pstmt.setString(3, hash);
                pstmt.setString(4, utilisateur.getNom());
                pstmt.setString(5, utilisateur.getPrenom());
                pstmt.setBoolean(6, utilisateur.getActif());
                pstmt.setInt(7, utilisateur.getIdUtilisateur());
            } else {
                pstmt.setString(1, utilisateur.getEmail());
                pstmt.setString(2, utilisateur.getIdentifiant());
                pstmt.setString(3, utilisateur.getNom());
                pstmt.setString(4, utilisateur.getPrenom());
                pstmt.setBoolean(5, utilisateur.getActif());
                pstmt.setInt(6, utilisateur.getIdUtilisateur());
            }

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la mise à jour : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Méthode privée pour charger les rôles de l'utilisateur
     */
    private void chargerRoles(Connection conn, Utilisateur utilisateur) throws SQLException {
        String queryRoles = "SELECT r.code_role FROM role r " +
                "JOIN role_utilisateur ru ON r.id_role = ru.id_role " +
                "WHERE ru.id_utilisateur = ?";

        try (PreparedStatement pstmtRoles = conn.prepareStatement(queryRoles)) {
            pstmtRoles.setLong(1, utilisateur.getIdUtilisateur());
            try (ResultSet rsRoles = pstmtRoles.executeQuery()) {
                while (rsRoles.next()) {
                    utilisateur.setCode_role(rsRoles.getString("code_role"));
                }
            }
        }
    }

    /**
     * Méthode privée pour charger toutes les permissions liées aux rôles de l'utilisateur
     */
    private void chargerPermissions(Connection conn, Utilisateur utilisateur) throws SQLException {
        String queryPerms = "SELECT DISTINCT p.code_permission FROM permission p " +
                "JOIN role_permission rp ON p.id_permission = rp.id_permission " +
                "JOIN role_utilisateur ru ON rp.id_role = ru.id_role " +
                "WHERE ru.id_utilisateur = ?";

        try (PreparedStatement pstmtPerms = conn.prepareStatement(queryPerms)) {
            pstmtPerms.setLong(1, utilisateur.getIdUtilisateur());
            try (ResultSet rsPerms = pstmtPerms.executeQuery()) {
                while (rsPerms.next()) {
                    utilisateur.setCode_permission(rsPerms.getString("code_permission"));
                }
            }
        }
    }
}