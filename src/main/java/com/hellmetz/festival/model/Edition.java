package com.hellmetz.festival.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "edition_festival")
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_edition")
    private int id;

    @Column(name = "annnee")
    private int annnee;

    @Column(name = "nom_edition")
    private String nom_edition;

    @Column(name = "date_debut")
    private Date date_debut;

    @Column(name = "date_fin")
    private Date date_fin;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "lieu")
    private String lieu;

    @Column(name = "theme")
    private String theme;

    @Column(name = "url_affiche_officielle")
    private String url_affiche_officielle;

    @Column(name = "budget_previsionnel")
    private BigDecimal budget_previsionnel;

    @Column(name = "jauge_maximale")
    private int jauge_maximale;

    @Column(name = "actif")
    private boolean actif;

    public Edition() {}

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
