package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "T_Garant")
public class Garant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_garant")
    private Long id_garant;

    @Column(name = "nom_g")
    private String nom_g;
    @Column(name = "prenom_g")
    private String prenom_g;
    @Column(name = "relation_g")
    private String relation_g;
    @Column(name = "cin_g")
    private String cin_g;

    @Column(name = "descrip_g")
    private String descrip_g;
   @OneToMany(mappedBy = "garant",fetch = FetchType.EAGER)
   private List <Demande_Pret> demandes;

  @OneToOne
  private Pret  pret;

    public Garant() {
    }

    public Garant(String nom_g, String prenom_g, String relation_g, String cin_g, String descrip_g) {
        this.nom_g = nom_g;
        this.prenom_g = prenom_g;
        this.relation_g = relation_g;
        this.cin_g = cin_g;
        this.descrip_g = descrip_g;

    }



    public String getPrenom_g() {
        return prenom_g;
    }

    public void setPrenom_g(String prenom_g) {
        this.prenom_g = prenom_g;
    }


    public Long getId_garant() {
        return id_garant;
    }

    public void setId_garant(Long id_garant) {
        this.id_garant = id_garant;
    }

    public String getNom_g() {
        return nom_g;
    }

    public void setNom_g(String nom_g) {
        this.nom_g = nom_g;
    }

    public String getRelation_g() {
        return relation_g;
    }

    public void setRelation_g(String relation_g) {
        this.relation_g = relation_g;
    }



    public String getDescrip_g() {
        return descrip_g;
    }

    public void setDescrip_g(String descrip_g) {
        this.descrip_g = descrip_g;
    }

    public String getCin_g() {
        return cin_g;
    }

    public void setCin_g(String cin_g) {
        this.cin_g = cin_g;
    }
}
