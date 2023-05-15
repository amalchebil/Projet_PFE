package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name ="T_EventCaisse")
public class EventCaisse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Long id_event;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_event")
   // @NotBlank(message = "Le champ type evenement ne doit pas etre vide.")
    private TypeEvent type_event;
    @JsonFormat(pattern="dd-MM-YYYY")
    @Column(name="date_event")
    //@NotBlank(message = "Le champ date ne doit pas etre vide.")
    private LocalDateTime date_event;
    @Column(name = "montant_event")
    @Min(value = 0, message = "Le montant minimum doit être supérieur ou égal à 0.")
   // @NotBlank(message = "Le champ montant ne doit pas etre vide.")
    private Double montant_event;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private Client client1;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agence_id")
    private Agence agence2;


}
