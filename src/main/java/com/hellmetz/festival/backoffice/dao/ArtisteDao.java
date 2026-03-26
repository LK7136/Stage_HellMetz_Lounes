package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Artiste;
import com.hellmetz.festival.backoffice.model.Groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtisteDao {

    /**
     * Récupère tous les artistes triés par nom de scène.
     */
    public List<Artiste> findAll() {
        List<Artiste> result = new ArrayList<>();
        String sql = "SELECT id_artiste, nom, prenom, nom_scene, biographie, url_photo, id_style, nationalite, cachet, url_facebook, url_instagram, url_spotify, exigences_catering FROM artiste ORDER BY nom_scene";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Artiste artiste = new Artiste();
                mapResultSetToArtiste(rs, artiste);
                result.add(artiste);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Récupère un artiste par son ID.
     */
    public Artiste findById(int id) {
        String sql = "SELECT * FROM artiste WHERE id_artiste = ?";
        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Artiste artiste = new Artiste();
                    mapResultSetToArtiste(rs, artiste);
                    return artiste;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Insère un nouvel artiste.
     */
    public void insert(Artiste artiste) {
        String sql = "INSERT INTO artiste (nom, prenom, nom_scene, biographie, url_photo, id_style, nationalite, cachet, url_facebook, url_instagram, url_spotify, exigences_catering) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, artiste);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour un artiste existant.
     */
    public void update(Artiste artiste) {
        String sql = "UPDATE artiste SET nom=?, prenom=?, nom_scene=?, biographie=?, url_photo=?, id_style=?, nationalite=?, cachet=?, url_facebook=?, url_instagram=?, url_spotify=?, exigences_catering=? WHERE id_artiste=?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, artiste);
            ps.setInt(13, artiste.getId()); // Le 13ème paramètre est l'ID pour le WHERE
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime un artiste via son ID.
     */
    public void delete(int id) {
        String sql = "DELETE FROM artiste WHERE id_artiste = ?";
        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode utilitaire pour remplir le PreparedStatement (Insert et Update).
     */
    private void fillPreparedStatement(PreparedStatement ps, Artiste artiste) throws SQLException {
        ps.setString(1, artiste.getNom());
        ps.setString(2, artiste.getPrenom());
        ps.setString(3, artiste.getNom_scene());
        ps.setString(4, artiste.getBiographie());
        ps.setString(5, artiste.getUrl_photo());
        ps.setInt(6, artiste.getId_style());
        ps.setString(7, artiste.getNationalite());
        ps.setBigDecimal(8, artiste.getCachet());
        ps.setString(9, artiste.getUrl_facebook());
        ps.setString(10, artiste.getUrl_instagram());
        ps.setString(11, artiste.getUrl_spotify());
        ps.setString(12, artiste.getExigences_catering());
    }


    /**
     Delete pour sup un artiste
     */
    public void delete(Artiste artiste) {
        String sql = "DELETE FROM artiste WHERE id_artiste = ?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {


            ps.setInt(1, artiste.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode utilitaire pour mapper le résultat SQL vers l'objet Java.
     */
    private void mapResultSetToArtiste(ResultSet rs, Artiste artiste) throws SQLException {
        artiste.setId(rs.getInt("id_artiste"));
        artiste.setNom(rs.getString("nom"));
        artiste.setPrenom(rs.getString("prenom"));
        artiste.setNom_scene(rs.getString("nom_scene"));
        artiste.setBiographie(rs.getString("biographie"));
        artiste.setUrl_photo(rs.getString("url_photo"));
        artiste.setId_style(rs.getInt("id_style"));
        artiste.setNationalite(rs.getString("nationalite"));
        artiste.setCachet(rs.getBigDecimal("cachet"));
        artiste.setUrl_facebook(rs.getString("url_facebook"));
        artiste.setUrl_instagram(rs.getString("url_instagram"));
        artiste.setUrl_spotify(rs.getString("url_spotify"));
        artiste.setExigences_catering(rs.getString("exigences_catering"));
    }
}