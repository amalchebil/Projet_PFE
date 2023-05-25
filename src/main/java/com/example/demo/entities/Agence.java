package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="T_Agence")
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agence_id")
    private Long id;
    @Column(name = "nom_agence")
    private String nom_agence;
    @Column(name = "gouvernorat")
    private String gouvernorat;
    @Column(name="caisse")
    private Double caisse;
    @JsonIgnore
    @OneToMany(mappedBy = "agc",fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
    private List<Client>clients;
@JsonIgnore
@OneToMany(mappedBy = "agence2",fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
private List<EventCaisse>  eventCaisses;
    @JsonIgnore
@OneToMany(mappedBy = "agence1",fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
    private List<EventClient>  eventClients;

@JsonIgnore
    @OneToMany(mappedBy = "agc1",fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
    private List<Projet>projets;


    @OneToMany(mappedBy = "agence",fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
    private List<User> users;
    public void transferetMontant(double montant) {
        this.caisse -= montant;
    }

    // Méthode pour ajouter un montant à la caisse
    public void ajouterMontant(double montant) {
        this.caisse += montant;
    }

    // Méthode pour soustraire un montant à la caisse
    public void soustraireMontant(double montant) {
        if (this.caisse >= montant) {
            this.caisse -= montant;
        } else {
            throw new IllegalArgumentException("Montant à soustraire supérieur à la caisse actuelle.");
        }
    }

    public Agence() {
    }

    public Agence(String nom_agence, String gouvernorat, Double caisse) {
        this.nom_agence = nom_agence;
        this.gouvernorat = gouvernorat;
        this.caisse = caisse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_agence() {
        return nom_agence;
    }

    public void setNom_agence(String nom_agence) {
        this.nom_agence = nom_agence;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public Double getCaisse() {
        return caisse;
    }

    public void setCaisse(Double caisse) {
        this.caisse = caisse;
    }
}
