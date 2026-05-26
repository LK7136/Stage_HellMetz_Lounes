package com.hellmetz.festival.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "style")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_style")
    private int id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    public Style() {}

    // Getters / Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLibelle() { return libelle; }

    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }


    public Style (int id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }
}

