package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Permission;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionDao {
    // Retourne toutes les permissions disponibles dans la table "permission"
    public List<Permission> getToutesLesPermissions() {
        List<Permission> liste = new ArrayList<>();
        String sql = "SELECT id_permission, code_permission, libelle, description "
                + "FROM hellmetz.permission ORDER BY code_permission";

        try (Connection cnx = ConnexionBD.getConnexion();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Permission p = new Permission();
                p.setIdPermission(rs.getLong("id_permission"));
                p.setCodePermission(rs.getString("code_permission"));
                p.setLibelle(rs.getString("libelle"));
                p.setDescription(rs.getString("description"));
                liste.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
}


}
