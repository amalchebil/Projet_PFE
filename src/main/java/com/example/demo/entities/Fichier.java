package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Entity
@Table(name = "T_fichier")
public class Fichier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fichier")
    private Long id_ficher;
    @Column(name = "titre")
    private String titre;

    @Column(name = "type")
    private String fileType;


    private byte[] data;


    @ManyToOne
    private Client client2;

    public Fichier() {
    }

   /* public Fichier(Long id_ficher, String titre, String fileType, byte[] data, Client client2) {
        this.id_ficher = id_ficher;
        this.titre = titre;
        this.fileType = fileType;
        this.data = data;
        this.client2 = client2;
    }
*/
    public Fichier(String titre, String fileType, byte[] data, Client client2) {
        this.titre = titre;
        this.fileType = fileType;
        this.data = data;
        this.client2 = client2;
    }

    public Long getId_ficher() {
        return id_ficher;
    }

    public void setId_ficher(Long id_ficher) {
        this.id_ficher = id_ficher;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Client getClient2() {
        return client2;
    }

    public void setClient2(Client client2) {
        this.client2 = client2;
    }
}