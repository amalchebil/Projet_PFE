package com.example.demo.entities;

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
@Table(name = "T_Pret")
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pret")
    private Long id_pret;

    @Column(name = "type_p")
    @Enumerated(EnumType.STRING)
    private Typepret typepret;
    @Column(name="nbr_echeance")
    private int nombreEcheances;
    @Column(name="montant_echeance")
    private double montantParEcheance;
    @Temporal(TemporalType.DATE)
    @Column(name="date_echeance")
    private Date datePremiereEcheance;


    @Column(name = "type_plan")
    @Enumerated(EnumType.STRING)
    private Typeplan type_plan;

    @Column(name="date_creation",nullable = false)
    private LocalDateTime date_creation;

    @Column(name = "montant_p")
    private double montant_p;

    @Column(name = "status_p")
    @Enumerated(EnumType.STRING)
    private Statu_p statuP;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "id_garant")
    private Garant garant;
    @OneToOne
    @JoinColumn(name = "id_demande")
    private Demande_Pret demandePret;

}
