package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.model.Style;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StyleDao {

    public List<Style> findAll() {

        List<Style> styles = new ArrayList<>();
        String sql = "select id_style, libelle, description from style ORDER BY id_style";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Style style = new Style(
                        rs.getInt("id_style"),
                        rs.getString("libelle"),
                        rs.getString("description")
                );

                styles.add(style);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return styles;
    }

}


