package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_Demande_Subvention")
public class Demande_Subvention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demande")
    private Long id_demande;

    @Column(name = "montant_net")
    private Double montant_net;
    @Column(name = "description")
    private String description;
    @Column(name="date_creation_demande")
    private LocalDateTime dateAttribut;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Statu status;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private Client client;

}
