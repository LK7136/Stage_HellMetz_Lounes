package com.hellmetz.festival.backoffice.model;

/**
 * ==============================================
 * Classe Groupe
 * ==============================================
 *
 * Cette classe permet de créer des objets "groupe"
 */
public class Groupe {

    /*
        Propriété de la classe Groupe
     */
    private int id;
    private String nom;
    private String description;
    private Boolean actif;
    private int id_concert;
    private int annee_creation;
    private String url_logo;
    private String site_web;
    private String url_facebook;
    private String url_instagram;
    private String url_youtube;
    private String url_spotify;
    private String email_contact;
    private String telephone_contact;
    private String url_fiche_technique;
    private String ville_origine;
    private String pays_origine;

    /*
        ENCAPSULATION - Permet de sécuriser les données
        Des contraintes d'intégrités peuvent être ajouter

        Les méthodes permettant d'accéder et modifier les données utiles
     */
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getActif() {
        return actif;
    }

    public int getId_concert() {
        return id_concert;
    }

    public int getAnnee_creation() {
        return annee_creation;
    }

    public String getUrl_logo() {
        return url_logo;
    }

    public String getSite_web() {
        return site_web;
    }

    public String getUrl_facebook() {
        return url_facebook;
    }

    public String getUrl_instagram() {
        return url_instagram;
    }

    public String getUrl_youtube() {
        return url_youtube;
    }

    public String getUrl_spotify() {
        return url_spotify;
    }

    public String getEmail_contact() {
        return email_contact;
    }

    public String getTelephone_contact() {
        return telephone_contact;
    }

    public String getUrl_fiche_technique() {
        return url_fiche_technique;
    }

    public String getVille_origine() {
        return ville_origine;
    }

    public String getPays_origine() {
        return pays_origine;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public void setId_concert(int id_concert) {
        this.id_concert = id_concert;
    }

    public void setAnnee_creation(int annee_creation) {
        this.annee_creation = annee_creation;
    }

    public void setUrl_logo(String url_logo) {
        this.url_logo = url_logo;
    }

    public void setSite_web(String site_web) {
        this.site_web = site_web;
    }

    public void setUrl_facebook(String url_facebook) {
        this.url_facebook = url_facebook;
    }

    public void setUrl_instagram(String url_instagram) {
        this.url_instagram = url_instagram;
    }

    public void setUrl_youtube(String url_youtube) {
        this.url_youtube = url_youtube;
    }

    public void setUrl_spotify(String url_spotify) {
        this.url_spotify = url_spotify;
    }

    public void setEmail_contact(String email_contact) {
        this.email_contact = email_contact;
    }

    public void setTelephone_contact(String telephone_contact) {
        this.telephone_contact = telephone_contact;
    }

    public void setUrl_fiche_technique(String url_fiche_technique) {
        this.url_fiche_technique = url_fiche_technique;
    }

    public void setVille_origine(String ville_origine) {
        this.ville_origine = ville_origine;
    }

    public void setPays_origine(String pays_origine) {
        this.pays_origine = pays_origine;
    }

    public Groupe(){
        this.id = 0;
        this.nom = "Undefined";
        this.description = "Undefined";
        this.actif = false;
        this.id_concert = 0;
        this.annee_creation = 2026;
        this.url_logo = "";
        this.site_web = "";
        this.url_facebook = "";
        this.url_instagram = "";
        this.url_youtube = "";
        this.url_spotify = "";
        this.email_contact = "";
        this.telephone_contact = "";
        this.url_fiche_technique = "";
        this.ville_origine = "Metz";
        this.pays_origine = "France";
    }

    public Groupe(int id, String nom_groupe, String description, Boolean actif, int id_concert, int annee_creation, String ville_origine, String pays_origine, String url_logo, String site_web, String url_facebook, String url_instagram, String url_youtube, String url_spotify, String email_contact, String telephone_contact, String url_fiche_technique){
        this.id = id;
        this.nom = nom_groupe;
        this.description = description;
        this.actif = actif;
        this.id_concert = id_concert;
        this.annee_creation = annee_creation;
        this.url_logo = url_logo;
        this.site_web = site_web;
        this.url_facebook = url_facebook;
        this.url_instagram = url_instagram;
        this.url_youtube = url_youtube;
        this.url_spotify = url_spotify;
        this.email_contact = email_contact;
        this.telephone_contact = telephone_contact;
        this.url_fiche_technique = url_fiche_technique;
        this.ville_origine = ville_origine;
        this.pays_origine = pays_origine;
    }

}
