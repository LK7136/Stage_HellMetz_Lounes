package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.model.Scene;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SceneDao {

    public List<Scene> findAll() {

        List<Scene> result = new ArrayList<>();

        String sql = "select id_scene, nom_scene, description, capacite, actif, type_scene, superficie_m2, url_plan_technique from scene ORDER BY nom_scene";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                /*
                    OPTION 1 - Utilisation du constructeur par défaut
                    Utilisation des méthodes de accesseurs et mutateurs
                 */
                /*
                Scene scene = new Scene();
                scene.setId(rs.getInt("id_scene"));
                scene.setNom(rs.getString("nom_scene"));
                scene.setDescription(rs.getString("description"));
                scene.setCapacite(rs.getInt("capacite"));
                scene.setActif(rs.getBoolean("actif"));
                scene.setType(rs.getString("type_scene"));
                scene.setSuperficie(rs.getInt("superficie_m2"));
                scene.setUrlPlanTechnique(rs.getString("url_plan_technique"));

                result.add(scene);
                */

                /*
                    OPTION 2 - Utilisation du constructeur uniquement
                 */
                Scene scene = new Scene(
                        rs.getInt("id_scene"),
                        rs.getString("nom_scene"),
                        rs.getString("description"),
                        rs.getInt("capacite"),
                        rs.getBoolean("actif"),
                        rs.getString("type_scene"),
                        rs.getInt("superficie_m2"),
                        rs.getString("url_plan_technique")
                );

                result.add(scene);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // pour les SIO1, on se contente de ça
        }

        return result;
    }

    /**
     * Récupère une scène spécifique par son identifiant.
     */
    public Scene findById(int id) {
        String sql = "SELECT * FROM scene WHERE id_scene = ?";
        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Scene scene = new Scene(
                            rs.getInt("id_scene"),
                            rs.getString("nom_scene"),
                            rs.getString("description"),
                            rs.getInt("capacite"),
                            rs.getBoolean("actif"),
                            rs.getString("type_scene"),
                            rs.getInt("superficie_m2"),
                            rs.getString("url_plan_technique")
                    );
                    return scene;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Insère une nouvelle scène dans la base de données.
     */
    public void insert(Scene scene) {
        String sql = "INSERT INTO scene (nom_scene, description, capacite, actif, type_scene, superficie_m2, url_plan_technique) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, scene);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour les informations d'une scène existante.
     */
    public void update(Scene scene) {
        String sql = "UPDATE scene SET nom_scene=?, description=?, capacite=?, actif=?, type_scene=?, superficie_m2=?, url_plan_technique=? WHERE id_scene=?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, scene);
            ps.setInt(8, scene.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
    Delete pour sup une scene
     */
     public void delete(Scene scene) {
        String sql = "DELETE FROM scene WHERE id_scene = ?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {


            ps.setInt(1, scene.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode utilitaire pour remplir les paramètres d'un PreparedStatement.
     */
    private void fillPreparedStatement(PreparedStatement ps, Scene scene) throws SQLException {
        ps.setString(1, scene.getNom());
        ps.setString(2, scene.getDescription());
        ps.setInt(3, scene.getCapacite());
        ps.setBoolean(4, scene.getActif());
        ps.setString(5, scene.getType());
        ps.setInt(6, scene.getSuperficie());
        ps.setString(7, scene.getUrlPlanTechnique());
    }

    // On ajoutera ensuite : delete
}