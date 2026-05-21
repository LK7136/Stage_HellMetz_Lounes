package com.hellmetz.festival.backoffice.model;

public class Style {
    private int id;
    private String libelle;
    private String description;

    // Getters / Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLibelle() { return libelle; }

    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Style () {
        this.id = 0;
        this.libelle = "Undefined";
        this.description = "Undefined";
    }

    public Style (int id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }
}

