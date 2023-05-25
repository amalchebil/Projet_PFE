package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="T_Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id_client;
    @Column(name = "nom_C",nullable = false)
    private String nom_c;
    @Column(name = "prenom_C",nullable = false)
    private String prenom_c;
    @Temporal(TemporalType.DATE)
    @Column(name="date_naiss",nullable = false)
    private Date date_naiss;

    @Column(name = "sexe_C")
    @Enumerated(EnumType.STRING)
    private Sexe sexe_c;
    @Column(name = "adresse_C",nullable = false)
    private String adresse_c;
    @Column(name = "numtel_C",nullable = false)
    private int numtel_c;
    @Column(name = "cin_C",unique = true,nullable = false)
    private String cin_c;
    @Column(name = "email_C",unique = true,nullable = false)
    @Email(message = "Le champ email doit être une adresse email valide.")
    private String email_c;

    @Column(name = "montant_emprunte")
    private double montant_emprunte;

    @Column(name = "montant_sub")
    private double montant_sub;
    @Column(name = "montant_projet")
    private double montant_projet;
    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
    private List<Pret> prets;
@JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
    private List<Subvention> subventions;

    @JsonIgnore
    @OneToMany (mappedBy= "client", fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
    private List<Demande_Pret> demandes_pret;
   @JsonIgnore
  @OneToMany (mappedBy= "client1", fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
   private List<EventCaisse> eventCaisses;
    @JsonIgnore
    @OneToMany (mappedBy= "client2", fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
    private List<EventClient> eventClients;


    @JsonIgnore
    @OneToMany (mappedBy= "client2", fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
    private Set<Fichier> fichiers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agence_id")
    private Agence agc;



@JsonIgnore
   @OneToMany(mappedBy = "client", fetch = FetchType.EAGER,cascade =  CascadeType.ALL )
   private List<Demande_Subvention> demandeSubventions;

    public void ajouterMontant(double montant) {
        this.montant_emprunte += montant;
    }




}
