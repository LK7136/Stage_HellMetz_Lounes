package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Edition;
import com.hellmetz.festival.backoffice.model.Style;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditionDao {
    public List<Edition> findAll() {

        List<Edition> editions = new ArrayList<>();
        String sql = "SELECT id_edition, nom_edition, actif FROM edition_festival ORDER BY nom_edition";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Edition edition = new Edition();
                edition.setId(rs.getInt("id_edition"));
                edition.setNom_edition(rs.getString("nom_edition"));
                edition.setActif(rs.getBoolean("actif"));

                editions.add(edition);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return editions;
    }
}

