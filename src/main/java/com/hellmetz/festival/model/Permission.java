package com.hellmetz.festival.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission")
    private long idPermission;

    @Column(name = "code_permission")
    private String codePermission;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    public Permission() {}

    // Getters et Setters (Indispensables pour le DAO)
    public long getIdPermission() { return idPermission; }
    public void setIdPermission(long idPermission) { this.idPermission = idPermission; }

    public String getCodePermission() { return codePermission; }
    public void setCodePermission(String codePermission) { this.codePermission = codePermission; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}