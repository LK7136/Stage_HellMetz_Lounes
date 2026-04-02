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
}


