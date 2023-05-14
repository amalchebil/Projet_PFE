package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_Projet")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column(name="projet_act")
@Enumerated(EnumType.STRING)
    private Act_Projet activite;
    @Column(name="projet_montant")
    private double montant;
    @Column(name="nbr_echeance")
    private int nombreEcheances;
    @Column(name="montant_echeance")
    private double montantParEcheance;
    @Temporal(TemporalType.DATE)
    @Column(name="date_echeance")
    private Date datePremiereEcheance;
    @Column(name="nbr_traite")
    private int nombreTraites;
    @Column(name="montant_traite")
    private double montantParTraite;
    @Temporal(TemporalType.DATE)
    @Column(name="date_traite")
    private Date dateTraitees;
    @Column(name="gouvernorat")
    private String gouvernorat;
    @Column(name="delegation")
    private String delegation;
    @Column(name="secteur")
    private String secteur;
@ManyToOne
private Agence agc1;

@OneToOne
@JoinColumn(name = "id_demande")
private Demande_Projet demandeProjet;

@OneToMany
    private List<Client> clients;

}
