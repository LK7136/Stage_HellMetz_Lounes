package com.hellmetz.festival.backoffice.dao;
import com.hellmetz.festival.backoffice.model.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RoleDao {


    // Retourne tous les rôles disponibles dans la table "role"
    public List<Role> getTousLesRoles() {
        List<Role> liste = new ArrayList<>();
        String sql = "SELECT id_role, code_role, libelle FROM hellmetz.role ORDER BY libelle";

        try (Connection cnx = ConnexionBD.getConnexion();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Role r = new Role();
                r.setIdRole(rs.getLong("id_role"));
                r.setCodeRole(rs.getString("code_role"));
                r.setLibelle(rs.getString("libelle"));
                liste.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    return liste;
}

// Retourne un rôle à partir de son identifiant
public Role getUnRole(long idRole) {
    String sql = "SELECT id_role, code_role, libelle FROM hellmetz.role WHERE id_role = ?";

    try (Connection cnx = ConnexionBD.getConnexion();
         PreparedStatement ps = cnx.prepareStatement(sql)) {

        ps.setLong(1, idRole);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Role r = new Role();
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

// Associe une liste de permissions à un rôle (table role_permission)
// Les anciennes associations du rôle sont d'abord supprimées.
public void associerPermissions(long idRole, List<Long> idsPermissions) {
    String sqlDelete = "DELETE FROM hellmetz.role_permission WHERE id_role = ?";
    String sqlInsert = "INSERT INTO hellmetz.role_permission (id_role, id_permission) VALUES (?, ?)";

    try (Connection cnx = ConnexionBD.getConnexion()) {
        cnx.setAutoCommit(false);

        try (PreparedStatement psDel = cnx.prepareStatement(sqlDelete)) {
            psDel.setLong(1, idRole);
            psDel.executeUpdate();
        }

        try (PreparedStatement psIns = cnx.prepareStatement(sqlInsert)) {
            for (Long idPerm : idsPermissions) {
                psIns.setLong(1, idRole);
                psIns.setLong(2, idPerm);
                psIns.addBatch();
            }
            psIns.executeBatch();
        }

        cnx.commit();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}