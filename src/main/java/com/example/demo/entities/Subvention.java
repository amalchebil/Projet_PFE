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

    @Column(name="date_creation",nullable = false)
    private LocalDateTime date_creation;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private Client client;
}
