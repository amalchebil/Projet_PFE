package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name="T_demandepret")
public class Demande_Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demande")
    private Long id_demande;

    @Column(name = "montant_net")

    private Double montant_net;
    @Column(name = "type_plan")
    @Enumerated(EnumType.STRING)
    private Typeplan type_plan;
    @Column(name = "type_pret")
    @Enumerated(EnumType.STRING)
    private Typepret type_pret;

    @Column(name="nbr_echeance")
    private int nombreEcheances;
    @Column(name="montant_echeance")
    private double montantParEcheance;


    @JsonFormat(pattern="dd-MM-YYYY")
    @Column(name="date_decaissement_pref")
    private Date date_decaissement_pref;
    @JsonFormat(pattern="dd-MM-YYYY")
    @Column(name="date_remb_pref")
    private Date date_remb_pref;
    @Column(name="date_creation_demande")
    private LocalDateTime dateAttribut;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Statu status;




    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_client", referencedColumnName="id_client")
    private Client client;


}
