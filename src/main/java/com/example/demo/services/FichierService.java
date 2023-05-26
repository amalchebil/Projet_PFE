package com.example.demo.services;

import com.example.demo.entities.Fichier;
import com.example.demo.entities.Garant;
import com.example.demo.repository.FichierRepository;
import com.example.demo.repository.GarantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class FichierService {
    @Autowired
    private FichierRepository gar ;


    @Transactional
    public Fichier AjouterFichier (Fichier f){

        return gar.save(f);
    }

    @Transactional
    public Fichier ModifierFichier (Fichier f){
        return gar.save(f);
    }

    @Transactional
    public void SupprimerFichier (Long id) {
        Fichier f = gar.getById(id);
        gar.delete(f);
    }

    @Transactional
    public List<Fichier> ListFichier(){
        return gar.findAll();
    }

    @Transactional
    public Optional<Fichier> AfficherFichier(Long  id) {
        return gar.findById(id);
    }
    @Transactional
    public Fichier updateFichier(Long id, Fichier p) {


        Fichier existingFichier = gar.findById(id)


                .orElseThrow();


        existingFichier.setData(p.getData());
        existingFichier.setFileType(p.getFileType());
existingFichier.setTitre(p.getTitre());
existingFichier.setClient2(p.getClient2());


        return gar.save(existingFichier);
    }
}
