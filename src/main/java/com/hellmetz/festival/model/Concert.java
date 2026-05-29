package com.hellmetz.festival.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "concert")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concert")
    private Long id;

    @Column(name = "statut")
    private String statut;

    @Column(name = "date_heure_debut")
    private Date dateHeureDebut;

    @Column(name = "date_heure_fin")
    private Date dateHeureFin;

    @Column(name = "heure_balance_debut")
    private Date  heureBalanceDebut;

    @Column(name = "heure_balance_fin")
    private Date heureBalanceFin;

    @Column(name = "decibels_max")
    private int decibelsMax;



    @ManyToOne
    @JoinColumn(name = "id_scene")
    private Scene scene;

    @ManyToOne
    @JoinColumn(name = "id_edition")
    private Edition edition;

    @OneToMany(mappedBy = "concert", fetch = FetchType.EAGER)
    private List<Groupe> groupes;


    public Concert(){}


    public Long getId() { return id; }
    public void setId(Long id) {this.id = id;}

    public String getStatut() { return statut; }
    public void setStatut(String statut) {this.statut = statut;}

    public Date getDateHeureDebut() { return dateHeureDebut; }
    public void setDateHeureDebut(Date dateHeureDebut) {this.dateHeureDebut = dateHeureDebut;}

    public Date getDateHeureFin() { return dateHeureFin; }
    public void setDateHeureFin(Date dateHeureFin) {this.dateHeureFin = dateHeureFin;}

    public Date getHeureBalanceDebut() { return heureBalanceDebut; }
    public void setHeureBalanceDebut(Date heureBalanceDebut) { this.heureBalanceDebut = heureBalanceDebut; }

    public Date getHeureBalanceFin() { return heureBalanceFin; }
    public void setHeureBalanceFin(Date heureBalanceFin) { this.heureBalanceFin = heureBalanceFin; }

    public int getDecibelsMax() { return decibelsMax; }
    public void setDecibelsMax(int decibelsMax) {this.decibelsMax = decibelsMax;}

    public List<Groupe> getGroupes() { return groupes; }
    public void setGroupes(List<Groupe> groupes) { this.groupes = groupes; }

    public Edition getEdition() { return edition; }
    public void setEdition(Edition edition) { this.edition = edition; }

    public Scene getScene() { return scene; }
    public void setScene(Scene scene) { this.scene = scene; }
}