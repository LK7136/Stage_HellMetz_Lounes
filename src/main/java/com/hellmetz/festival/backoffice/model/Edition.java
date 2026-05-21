package com.hellmetz.festival.backoffice.model;

import java.math.BigDecimal;
import java.util.Date;

public class Edition {
    private int id;
    private int annnee;
    private String nom_edition;
    private Date date_debut;
    private Date date_fin;
    private BigDecimal longitude;
    private String lieu;
    private String theme;
    private String url_affiche_officielle;
    private BigDecimal budget_previsionnel;
    private int jauge_maximale;
    private boolean actif;


    // Getters / Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getId_edition() { return annnee; }
    public void setAnnnee(int annnee) { this.annnee = annnee; }

    public String getNom_edition() { return nom_edition; }
    public void setNom_edition(String nom_edition) { this.nom_edition = nom_edition; }

    public Date getDate_debut() { return date_debut; }
    public void setDate_debut(Date date_debut) { this.date_debut = date_debut; }

    public Date getDate_fin() { return date_fin; }
    public void setDate_fin(Date date_fin) { this.date_fin = date_fin; }

    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }

    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }

    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }

    public String getUrl_affiche_officielle() { return url_affiche_officielle; }
    public void setUrl_affiche_officielle(String url_affiche_officielle) { this.url_affiche_officielle = url_affiche_officielle; }

    public BigDecimal getBudget_previsionnel() { return budget_previsionnel; }
    public void setBudget_previsionnel(BigDecimal budget_previsionnel) { this.budget_previsionnel = budget_previsionnel; }

    public int getJauge_maximale() { return jauge_maximale; }
    public void setJauge_maximale(int jauge_maximale) { this.jauge_maximale = jauge_maximale; }

    public boolean getActif() { return actif; }
    public void setActif(boolean actif) { this.actif = actif; }






    public Edition () {
        this.id = 0;
        this.annnee = 0;
        this.nom_edition = "Undefined";
        this.date_debut = null;
        this.date_fin = null;
        this.longitude = BigDecimal.ZERO;
        this.lieu = "Undefined";
        this.theme = "Undefined";
        this.url_affiche_officielle = "Undefined";
        this.budget_previsionnel = BigDecimal.ZERO;
        this.jauge_maximale = 0;
        this.actif = false;
    }

    public Edition (int id, int annnee, String nom_edition, Date date_debut, Date date_fin, BigDecimal longitude, String lieu, String theme, String url_affiche_officielle, BigDecimal budget_previsionnel, int jauge_maximale, boolean actif) {
        this.id = id;
        this.annnee = annnee;
        this.nom_edition = nom_edition;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.longitude = longitude;
        this.lieu = lieu;
        this.theme = theme;
        this.url_affiche_officielle = url_affiche_officielle;
        this.budget_previsionnel = budget_previsionnel;
        this.jauge_maximale = jauge_maximale;
        this.actif = actif;
    }
}
