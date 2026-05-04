package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.permission;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class permissionDao {
    // Retourne toutes les permissions disponibles dans la table "permission"
    public List<permission> getToutesLesPermissions() {
        List<permission> liste = new ArrayList<>();
        String sql = "SELECT id_permission, code_permission, libelle, description "
                + "FROM hellmetz.permission ORDER BY code_permission";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                permission p = new permission();
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



