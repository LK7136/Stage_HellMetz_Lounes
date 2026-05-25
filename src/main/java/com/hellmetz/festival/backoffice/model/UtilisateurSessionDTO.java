package com.hellmetz.festival.backoffice.model;

import com.hellmetz.festival.model.Utilisateur;

import java.io.Serializable;
import java.util.List;

/**
 * DTO (Data Transfer Object) représentant l'utilisateur tel qu'il est
 * stocké dans la session HTTP. Contrairement à la classe Utilisateur
 * (qui correspond à la table en base et contient le hash du mot de passe),
 * ce DTO ne contient QUE les informations strictement nécessaires aux vues.
 *
 * Aucun champ sensible (mot de passe, sel, jeton, ...) ne doit jamais
 * être ajouté ici.
 */
public class UtilisateurSessionDTO implements Serializable {

    private long        idUtilisateur;
    private String      nom;
    private String      prenom;
    private String      identifiant;
    private String      email;
    private List<String> roles;       // codes des rôles (ex: ["ADMIN"])
    private boolean     isAdmin;

    // Constructeur vide (nécessaire pour la sérialisation de session)
    public UtilisateurSessionDTO() {}

    // Constructeur de confort à partir d'un Utilisateur "métier"
    public UtilisateurSessionDTO(Utilisateur u, List<String> roles) {
        this.idUtilisateur = u.getIdUtilisateur();
        this.nom           = u.getNom();
        this.prenom        = u.getPrenom();
        this.identifiant   = u.getIdentifiant();
        this.email         = u.getEmail();
        this.roles         = roles;
        this.isAdmin       = u.getIsAdmin();
    }

    // ----- Getters / setters -----
    public long   getIdUtilisateur()        { return idUtilisateur; }
    public void   setIdUtilisateur(long v)  { this.idUtilisateur = v; }
    public String getNom()                  { return nom; }
    public void   setNom(String v)          { this.nom = v; }
    public String getPrenom()               { return prenom; }
    public void   setPrenom(String v)       { this.prenom = v; }
    public String getIdentifiant()          { return identifiant; }
    public void   setIdentifiant(String v)  { this.identifiant = v; }
    public String getEmail()                { return email; }
    public void   setEmail(String v)        { this.email = v; }
    public List<String> getRoles()          { return roles; }
    public void   setRoles(List<String> v)  { this.roles = v; }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}