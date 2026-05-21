package com.hellmetz.festival.backoffice.model;

import java.math.BigDecimal;

public class Artiste {
    private int id;
    private String nom;
    private String prenom ;
    private String nom_scene ;
    private String biographie ;
    private String url_photo;
    private int id_style;
    private String nationalite;
    private BigDecimal cachet ;
    private String url_facebook;
    private String url_instagram;
    private String url_spotify;
    private String exigences_catering;
    private int id_groupe;
    private String styleLibelle;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) { this.prenom = prenom;}

    public String getNom_scene() {return nom_scene;}

    public void setNom_scene(String nom_scene) { this.nom_scene = nom_scene;}

    public String getBiographie() {return biographie;}

    public void setBiographie(String biographie) { this.biographie = biographie;}

    public String getUrl_photo() {return url_photo;}

    public void setUrl_photo(String url_photo) {this.url_photo = url_photo;}

    public int getId_style() {return id_style;}

    public void setId_style(int id_style) {this.id_style = id_style;}

    public String getNationalite() {return nationalite;}

    public void setNationalite(String nationalite) {this.nationalite = nationalite;}

    public BigDecimal getCachet() {return cachet;}

    public void setCachet(BigDecimal cachet) {this.cachet = cachet;}

    public String getUrl_facebook() {return url_facebook;}

    public void setUrl_facebook(String url_facebook) {this.url_facebook = url_facebook;}

    public String getUrl_instagram() {return url_instagram;}

    public void setUrl_instagram(String url_instagram) {this.url_instagram = url_instagram;}

    public String getUrl_spotify() {return url_spotify;}

    public void setUrl_spotify(String url_spotify) {this.url_spotify = url_spotify;}

    public String getExigences_catering() {return exigences_catering;}

    public void setExigences_catering(String exigences_catering) {this.exigences_catering = exigences_catering;}

    public int getId_groupe() {return id_groupe; }

    public void setId_groupe(int id_groupe) { this.id_groupe = id_groupe; }

    public String getStyleLibelle() { return styleLibelle; }

    public void setStyleLibelle(String styleLibelle) { this.styleLibelle = styleLibelle; }

    public Artiste () {
        this.id = 0;
        this.nom = "Undefined";
        this.prenom = "Undefined";
        this.nom_scene = "Undefined";
        this.biographie = "Undefined";
        this.url_photo = "Undefined";
        this.id_style = 0;
        this.nationalite = "Undefined";
        this.cachet = BigDecimal.ZERO;
        this.url_facebook = "Undefined";
        this.url_instagram = "Undefined";
        this.url_spotify = "Undefined";
        this.exigences_catering = "Undefined";
        this.id_groupe = 0;

    }

    public Artiste (int id, String nom, String prenom, String nom_scene, String biographie, String url_photo, int id_style, String nationalite, BigDecimal cachet, String url_facebook, String url_instagram, String url_spotify, String exigences_catering, int id_groupe) {
        this.id                 = id;
        this.nom                = nom;
        this.prenom             = prenom;
        this.nom_scene          = nom_scene;
        this.biographie         = biographie;
        this.url_photo          = url_photo;
        this.id_style           = id_style;
        this.nationalite        = nationalite;
        this.cachet             = cachet;
        this.url_facebook       = url_facebook;
        this.url_instagram      = url_instagram;
        this.url_spotify        = url_spotify;
        this.exigences_catering = exigences_catering;
        this.id_groupe          = id_groupe;

    }


}


