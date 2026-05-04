package com.hellmetz.festival.backoffice.model;

public class role {
    private long idRole;
    private String codeRole;
    private String libelle;

    public role() {}

    public long getIdRole() { return idRole; }
    public void setIdRole(long idRole) { this.idRole = idRole; }

    public String getCodeRole() { return codeRole; }
    public void setCodeRole(String codeRole) { this.codeRole = codeRole; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
}