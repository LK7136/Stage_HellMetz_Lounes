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
        String sql ="SELECT a.id_artiste, a.nom, a.prenom, a.nom_scene, a.biographie, a.url_photo, a.id_style, a.nationalite, a.cachet, a.url_facebook, a.url_instagram, a.url_spotify, a.exigences_catering, a.id_groupe, s.libelle AS style_libelle FROM artiste a LEFT JOIN style s ON a.id_style = s.id_style ORDER BY a.nom_scene";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Artiste artiste = new Artiste();
                mapResultSetToArtiste(rs, artiste);
                artiste.setStyleLibelle(rs.getString("style_libelle"));
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
     * Récupère la liste des artistes par leurs id_groupe (liste les artistes d'un meme groupe)
     */

    public List<Artiste> findArtistesByGroupe(int id_groupe) {
        List<Artiste> result = new ArrayList<>();
        String sql = "SELECT * FROM artiste WHERE id_groupe = ?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id_groupe);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Artiste artiste = new Artiste();
                    mapResultSetToArtiste(rs, artiste);
                    result.add(artiste);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Insère un nouvel artiste.
     */
    public void insert(Artiste artiste) {
        String sql = "INSERT INTO artiste (nom, prenom, nom_scene, biographie, url_photo, id_style, nationalite, cachet, url_facebook, url_instagram, url_spotify, exigences_catering, id_groupe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        String sql = "UPDATE artiste SET nom=?, prenom=?, nom_scene=?, biographie=?, url_photo=?, id_style=?, nationalite=?, cachet=?, url_facebook=?, url_instagram=?, url_spotify=?, exigences_catering=?, id_groupe=? WHERE id_artiste=?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, artiste);
            ps.setInt(14, artiste.getId()); // Le 13ème paramètre est l'ID pour le WHERE
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

        //changemant pour que si id style = 0 sa envoi null en bdd
        if (artiste.getId_style() == 0) {
            ps.setNull(6, java.sql.Types.INTEGER);
        } else {
            ps.setInt(6, artiste.getId_style());
        }
        ps.setString(7, artiste.getNationalite());
        ps.setBigDecimal(8, artiste.getCachet());
        ps.setString(9, artiste.getUrl_facebook());
        ps.setString(10, artiste.getUrl_instagram());
        ps.setString(11, artiste.getUrl_spotify());
        ps.setString(12, artiste.getExigences_catering());

        //changemant pour que si id group = 0 sa envoi null en bdd
        if (artiste.getId_groupe() == 0) {
            ps.setNull(13, java.sql.Types.INTEGER);
        } else {
            ps.setInt(13, artiste.getId_groupe());
        }
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
        artiste.setId_groupe(rs.getInt("id_groupe"));

    }
}