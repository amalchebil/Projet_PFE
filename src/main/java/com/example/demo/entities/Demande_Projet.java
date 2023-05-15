package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "T_DemandeProjet")
public class Demande_Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demande")
    private Long id_demande;

    @Column(name = "activité")
    @Enumerated(EnumType.STRING)
    private Act_Projet actProjet;

    @Column(name="projet_montant")
    private double montant;
    @Column(name="nbr_echeance")
    private int nombreEcheances;
    @Column(name="montant_echeance")
    private double montantParEcheance;
    @Temporal(TemporalType.DATE)
    @Column(name="date_echeance")
    private Date datePremiereEcheance;
    @Column(name="gouvernorat")
    private String gouvernorat;
    @Column(name="date_creation_demande")
    private LocalDateTime dateAttribut;

    @Column(name = "description")
    private String descp;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Statu status;

    @OneToOne
    private Projet projet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private Client client;
}
