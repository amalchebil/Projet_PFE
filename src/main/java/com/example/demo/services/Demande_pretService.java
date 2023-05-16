package com.example.demo.services;
import java.time.LocalDateTime;

import com.example.demo.entities.Demande_Pret;
import com.example.demo.entities.Pret;
import com.example.demo.entities.Statu;
import com.example.demo.repository.Demande_pretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class Demande_pretService {
    @Autowired
    private Demande_pretRepository dmd ;

    @Transactional
    public Demande_Pret AjouterDemande (Demande_Pret f) {
        f.setDateAttribut(LocalDateTime.now());
        f.setStatus(Statu.En_attente);

        return dmd.save(f);
    }

    @Transactional
    public Demande_Pret ModifierDemande (Demande_Pret f){
        return dmd.save(f);
    }

    @Transactional
    public void SupprimerDemande (Long id) {
        Demande_Pret f = dmd.getById(id);
        dmd.delete(f);
    }

    @Transactional
    public List<Demande_Pret> ListDemande(){
        return dmd.findAll();
    }

    @Transactional
    public Optional<Demande_Pret> AfficherDemandePret(Long  id){
        return dmd.findById(id);
    }
    @Transactional
    public Demande_Pret getDemande_PretById(long id) {
        Optional<Demande_Pret> demandePret = dmd.findById(id);
        if (demandePret.isPresent()) {
            return demandePret.get();
        } else {
            throw new NoSuchElementException("Demande Pret introuvable pour l'ID: " + id); // Lance une exception si le pret n'existe pas
        }
    }
}
