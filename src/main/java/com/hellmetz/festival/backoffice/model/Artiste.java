package com.hellmetz.festival.model;

import jakarta.persistence.*;  // Importez les annotations JPA/Hibernate
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

// Question : pourquoi ces deux annotations sont-elles nécessaires ?
@Entity          // A vous d'expliquer le rôle de chaque annotation
@Table(name = "artiste")
public class Artiste {

    // Question : que signifie GenerationType.IDENTITY ?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artiste")
    private int id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;

    @Column(name = "nom_scene ")
    private String nom_scene;

    @Column(name = "biographie")
    private String biographie;

    @Column(name = "url_photo")
    private String url_photo;

    @Column(name = "nationalite")
    private String nationalite;

    @Column(name = "cachet")
    private BigDecimal cachet;

    @Column(name = "url_facebook")
    private String url_facebook;

    @Column(name = "url_instagram")
    private String url_instagram;

    @Column(name = "url_spotify")
    private String url_spotify;

    @Column(name = "exigences_catering")
    private String exigences_catering;


    // Relation vers style
    @ManyToOne
    @JoinColumn(name = "id_style") // clé étrangère en BDD
    private Style style;

    // A vous : ajoutez la relation avec Groupe
    @ManyToOne
    @JoinColumn(name = "id_groupe")
    private Groupe groupe;

    // Indice : un artiste peut appartenir à plusieurs groupes
    // Quelle annotation utiliserez-vous ? @OneToMany ? @ManyToMany ?

    public Artiste() {}  // Constructeur vide obligatoire pour Hibernate

    // A vous : ajoutez les getters et setters

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}

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

    public int getId_style() {return style;}

    public void setId_style(int style) {this.style = style;}

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

}
