package com.hellmetz.festival.backoffice.model;

public class permission {
    private long idPermission; // Changé en long pour correspondre aux IDs SQL
    private String codePermission; // String pour les codes type "ADMIN_ALL"
    private String libelle;
    private String description;



    // Getters et Setters (Indispensables pour le DAO)
    public long getIdPermission() { return idPermission; }
    public void setIdPermission(long idPermission) { this.idPermission = idPermission; }

    public String getCodePermission() { return codePermission; }
    public void setCodePermission(String codePermission) { this.codePermission = codePermission; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


    public permission() {}
}