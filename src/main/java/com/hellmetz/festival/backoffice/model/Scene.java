package com.hellmetz.festival.backoffice.model;

public class Scene {

    private int id;

    private String nom;

    private String description;

    private int capacite;

    private boolean actif;

    private String type;

    private int superficie;

    private String urlPlanTechnique;




    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacite() {
        return capacite;
    }

    public boolean getActif() {
        return actif;
    }

    public String getType() {
        return type;
    }

    public int getSuperficie() {
        return superficie;
    }

    public String getUrlPlanTechnique() {
        return urlPlanTechnique;
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

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public void setUrlPlanTechnique(String urlPlanTechnique) {
        this.urlPlanTechnique = urlPlanTechnique;
    }




    public Scene(){
        this.id = 0;
        this.nom = "Undefined";
        this.description = "Undefined";
        this.capacite = 0;
        this.actif = false;
        this.type = "Undefined";
        this.superficie = 0;
        this.urlPlanTechnique = "Undefined";
    }

    public Scene(int id, String nom, String description, int capacite, boolean actif, String type, int superficie, String urlPlanTechnique){
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.capacite = capacite;
        this.actif = actif;
        this.type = type;
        this.superficie = superficie;
        this.urlPlanTechnique = urlPlanTechnique;
    }
}
