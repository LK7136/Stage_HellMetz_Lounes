package com.hellmetz.festival.backoffice.model;

import java.util.Date;

public class Concert {
    private int id_scene;
    private int id_edition;
    private int id_concert;
    private String statut;
    private Date date_heure_debut;
    private Date date_heure_fin;
    private Date date_balance_debut;
    private Date date_balance_fin;
    private int decibels_max;

    public int getId_scene() { return id_scene; }
    public int getId_edition() { return id_edition; }
    public int getId_concert() { return id_concert; }
    public String getStatut() { return statut; }
    public Date getDate_heure_debut() { return date_heure_debut; }
    public Date getDate_heure_fin() { return date_heure_fin; }
    public Date getDate_balance_debut() { return date_balance_debut; }
    public Date getDate_balance_fin() { return date_balance_fin; }
    public int getDecibels_max() { return decibels_max; }
    public void setId_concert(int id_concert) { this.id_concert = id_concert; }


    private String nom_scene;
    private String nom_groupe;

    // Ajoute ces getters et setters
    public String getNom_scene() { return nom_scene; }
    public void setNom_scene(String nom_scene) { this.nom_scene = nom_scene; }

    public String getNom_groupe() { return nom_groupe; }
    public void setNom_groupe(String nom_groupe) { this.nom_groupe = nom_groupe; }

    public Concert() {
        this.id_scene = 0;
        this.id_edition = 0;
        this.id_concert = 0;
        this.statut = "Programmé";
        this.date_heure_debut = null;
        this.date_heure_fin = null;
        this.date_balance_debut = null;
        this.date_balance_fin = null;
        this.decibels_max = 95;
    }

    public Concert(int id_scene, int id_edition, int id_concert, String statut,
                   Date date_heure_debut, Date date_heure_fin,
                   Date date_balance_debut, Date date_balance_fin, int decibels_max) {
        this.id_scene = id_scene;
        this.id_edition = id_edition;
        this.id_concert = id_concert;
        this.statut = statut;
        this.date_heure_debut = date_heure_debut;
        this.date_heure_fin = date_heure_fin;
        this.date_balance_debut = date_balance_debut;
        this.date_balance_fin = date_balance_fin;
        this.decibels_max = decibels_max;
    }
}