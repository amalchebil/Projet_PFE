package com.example.demo.services;

import com.example.demo.entities.Demande_Projet;
import com.example.demo.entities.Projet;
import com.example.demo.entities.Statu;
import com.example.demo.entities.Subvention;
import com.example.demo.repository.Demande_pretRepository;
import com.example.demo.repository.Demande_projetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class Demande_projetService {
    @Autowired
    private Demande_projetRepository dmd ;

    @Transactional
    public Demande_Projet AjouterDemande (Demande_Projet f) {
        f.setDateAttribut(LocalDateTime.now());
        f.setStatus(Statu.En_attente);

        return dmd.save(f);
    }

    @Transactional
    public Demande_Projet ModifierDemande (Demande_Projet f){
        return dmd.save(f);
    }

    @Transactional
    public void SupprimerDemande (Long id) {
        Demande_Projet f = dmd.getById(id);
        dmd.delete(f);
    }

    @Transactional
    public List<Demande_Projet> ListDemande(){
        return dmd.findAll();
    }

    @Transactional
    public Optional<Demande_Projet> AfficherDemandePret(Long  id){
        return dmd.findById(id);
    }

    public Demande_Projet updateDemande_Projet(Long id, Demande_Projet p) {


        Demande_Projet existingDemande_Projet = dmd.findById(id)


                .orElseThrow();


        existingDemande_Projet.setActProjet(p.getActProjet());
        existingDemande_Projet.setProjet(p.getProjet());
        existingDemande_Projet.setGouvernorat(p.getGouvernorat());
        existingDemande_Projet.setDescp(p.getDescp());



        return dmd.save(existingDemande_Projet);
    }


    @Transactional
    public Demande_Projet getDemande_ProjetById(long id) {
        Optional<Demande_Projet> projet = dmd.findById(id);
        if (projet.isPresent()) {
            return projet.get();
        } else {
            throw new NoSuchElementException("Demande Projet introuvable pour l'ID: " + id);
        }
    }
}
