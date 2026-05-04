package com.hellmetz.festival.backoffice.model;

import java.time.Instant;
import java.util.Date;

public class Utilisateur {

    private int idUtilisateur;
    private String email;
    private String identifiant;
    private String nom;
    private String prenom;
    private Boolean actif;
    private Date date_creation;
    private Date dernier_connexion;
    private String code_role;
    private String code_permission;
    private String MotDePasse;

    // A vous de compléter les différentes propriétés de la classe sans mettre le mot de passe


    // Getters et Setters à compléter


    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDernier_connexion() {
        return dernier_connexion;
    }

    public void setDernier_connexion(Date date_creation) {
        this.dernier_connexion = date_creation;
    }

    public String getCode_role() {
        return code_role;
    }

    public void setCode_role(String code_role) {
        this.code_role = code_role;
    }

    public String getCode_permission() {
        return code_permission;
    }

    public void setCode_permission(String code_permission) {
        this.code_permission = code_permission;

    }
    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String MotDePasse) {
        this.identifiant = MotDePasse;
    }


    // Constructeurs à compléter
    public Utilisateur() {
        this.idUtilisateur = 0;
        this.email = "Undefined";
        this.identifiant = "Undefined";
        this.nom = "Undefined";
        this.prenom = "Undefined";
        this.actif = false;
        this.date_creation = new Date();
        this.dernier_connexion = new Date();
        this.code_role = "Undefined";
        this.code_permission = "Undefined";
        this.MotDePasse="Undefined";
    }

    public Utilisateur(int idUtilisateur, String email, String identifiant, String nom, String prenom, Boolean actif, Date date_creation, Date dernier_connexion, String code_role, String code_permission, String MotDePasse) {
        this.idUtilisateur = idUtilisateur;
        this.email = email;
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.actif = actif;
        this.date_creation = date_creation;
        this.dernier_connexion = dernier_connexion;
        this.code_role = code_role;
        this.code_permission = code_permission;
        this.MotDePasse=MotDePasse;

    }

}











