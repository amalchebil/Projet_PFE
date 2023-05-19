package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_Subvention")
public class Subvention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "montant_sub")
    private Double montant_sub;
    @Column(name = "description")
    private String description;

    @Column(name="date_creation",nullable = false)
    private LocalDateTime date_creation;

    @Column(name = "status_s")
    @Enumerated(EnumType.STRING)
    private Statu_p statuS;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToOne
    @JoinColumn(name = "id_demande")
    private Demande_Subvention demandeSubvention;
}
