package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour la gestion des rôles et de leurs permissions
 */
public class RoleDao {

    /**
     * Récupère la liste de tous les rôles disponibles
     * Utilisé notamment pour remplir le formulaire d'inscription
     */
    public List<role> getTousLesRoles() {
        List<role> roles = new ArrayList<>();
        String sql = "SELECT id_role, code_role, libelle FROM hellmetz.role";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                role r = new role();
                r.setIdRole(rs.getLong("id_role"));
                r.setCodeRole(rs.getString("code_role"));
                r.setLibelle(rs.getString("libelle"));
                roles.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    /**
     * Récupère un rôle spécifique par son identifiant
     */
    public role getUnRole(long idRole) {
        String sql = "SELECT id_role, code_role, libelle FROM hellmetz.role WHERE id_role = ?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setLong(1, idRole);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    role r = new role();
                    r.setIdRole(rs.getLong("id_role"));
                    r.setCodeRole(rs.getString("code_role"));
                    r.setLibelle(rs.getString("libelle"));
                    return r;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Associe une liste de permissions à un rôle (table role_permission)
     * Supprime les anciennes associations avant d'insérer les nouvelles
     */
    public void associerPermissions(long idRole, List<Long> idsPermissions) {
        String sqlDelete = "DELETE FROM hellmetz.role_permission WHERE id_role = ?";
        String sqlInsert = "INSERT INTO hellmetz.role_permission (id_role, id_permission) VALUES (?, ?)";

        try (Connection cn = ConnectionFactory.getConnection()) {
            // Désactivation de l'auto-commit pour gérer une transaction unique
            cn.setAutoCommit(false);

            // 1. Suppression des habilitations existantes pour ce rôle
            try (PreparedStatement psDel = cn.prepareStatement(sqlDelete)) {
                psDel.setLong(1, idRole);
                psDel.executeUpdate();
            }

            // 2. Insertion des nouvelles habilitations en mode Batch pour la performance
            try (PreparedStatement psIns = cn.prepareStatement(sqlInsert)) {
                for (Long idPerm : idsPermissions) {
                    psIns.setLong(1, idRole);
                    psIns.setLong(2, idPerm);
                    psIns.addBatch();
                }
                psIns.executeBatch();
            }

            // Validation de la transaction
            cn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}