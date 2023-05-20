package com.example.demo.services;

import com.example.demo.entities.Agence;

import com.example.demo.entities.Subvention;
import com.example.demo.repository.AgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AgenceService {
    @Autowired
    private AgenceRepository agr;

    @Transactional
    public Agence AjouterAgence(Agence f){
        return agr.save(f);
    }
    @Transactional
    public Agence ModifierAgence (Agence f){
        return agr.save(f);
    }

    @Transactional
    public void SupprimerAgence (Long id) {
        Agence f = agr.getById(id);
        agr.delete(f);
    }

    @Transactional
    public List<Agence> ListAgence(){
        return agr.findAll();
    }

    @Transactional
    public Optional<Agence> AfficherAgence(Long  id) {
        return agr.findById(id);
    }
@Transactional
    public Agence getAgenceById(long id) {
        Optional<Agence> agence = agr.findById(id); // Récupère l'Agence correspondante dans la base de données par son ID
        if (agence.isPresent()) {
            return agence.get(); // Retourne l'Agence si elle existe
        } else {
            throw new NoSuchElementException("Agence introuvable pour l'ID: " + id); // Lance une exception si l'Agence n'existe pas
        }
    }

    public Agence updateAgence(Long id, Agence p) {


        Agence existingAgence = agr.findById(id)


                .orElseThrow();


        existingAgence.setNom_agence(p.getNom_agence());
        existingAgence.setCaisse(p.getCaisse());
        existingAgence.setGouvernorat(p.getGouvernorat());




        return agr.save(existingAgence);
    }

}
