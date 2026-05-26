package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.model.Concert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConcertDao {

    public List<Concert> findAll() {
        List<Concert> result = new ArrayList<>();

        String sql = "SELECT c.id_scene, c.id_edition, c.id_concert, c.statut, c.date_heure_debut, c.date_heure_fin, c.heure_balance_debut, c.heure_balance_fin, c.decibels_max, s.nom_scene, g.nom_groupe, e.nom_edition " +
                "FROM concert c " +
                "LEFT JOIN edition_festival e ON c.id_edition = e.id_edition " +
                "LEFT JOIN scene s ON c.id_scene = s.id_scene " +
                "JOIN groupe g ON g.id_concert = c.id_concert " +
                "ORDER BY c.id_concert";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Concert concert = new Concert(
                        rs.getInt("id_scene"), rs.getInt("id_edition"),
                        rs.getInt("id_concert"), rs.getString("statut"),
                        rs.getTimestamp("date_heure_debut"), rs.getTimestamp("date_heure_fin"),
                        rs.getTimestamp("heure_balance_debut"), rs.getTimestamp("heure_balance_fin"),
                        rs.getInt("decibels_max")
                );
                concert.setNom_scene(rs.getString("nom_scene"));
                concert.setNom_groupe(rs.getString("nom_groupe"));
                result.add(concert);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Concert findById(int id) {
        String sql = "SELECT * FROM concert WHERE id_concert = ?";
        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Concert(
                            rs.getInt("id_scene"), rs.getInt("id_edition"),
                            rs.getInt("id_concert"), rs.getString("statut"),
                            rs.getTimestamp("date_heure_debut"), rs.getTimestamp("date_heure_fin"),
                            rs.getTimestamp("heure_balance_debut"), rs.getTimestamp("heure_balance_fin"),
                            rs.getInt("decibels_max")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Concert concert) {
        String sql = "INSERT INTO concert (id_scene, id_edition, statut, date_heure_debut, date_heure_fin, heure_balance_debut, heure_balance_fin, decibels_max) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, concert);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Concert concert) {
        String sql = "UPDATE concert SET id_scene=?, id_edition=?, statut=?, date_heure_debut=?, date_heure_fin=?, heure_balance_debut=?, heure_balance_fin=?, decibels_max=? WHERE id_concert=?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, concert);
            ps.setInt(9, concert.getId_concert());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     Delete pour sup un grp
     */
    public void delete(Concert concert) {
        String sql = "DELETE FROM concert WHERE id_concert = ?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {


            ps.setInt(1, concert.getId_concert());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillPreparedStatement(PreparedStatement ps, Concert concert) throws SQLException {
        ps.setInt(1, concert.getId_scene());
        ps.setInt(2, concert.getId_edition());
        ps.setString(3, concert.getStatut());
        ps.setTimestamp(4, toSqlTimestamp(concert.getDate_heure_debut()));
        ps.setTimestamp(5, toSqlTimestamp(concert.getDate_heure_fin()));
        ps.setTimestamp(6, toSqlTimestamp(concert.getDate_balance_debut()));
        ps.setTimestamp(7, toSqlTimestamp(concert.getDate_balance_fin()));
        ps.setInt(8, concert.getDecibels_max());
    }

    private java.sql.Timestamp toSqlTimestamp(java.util.Date date) {
        return date != null ? new java.sql.Timestamp(date.getTime()) : null;
    }
}
// test de push