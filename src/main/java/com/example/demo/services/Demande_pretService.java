package com.example.demo.services;
import java.time.LocalDateTime;

import com.example.demo.entities.Demande_Pret;
import com.example.demo.entities.Pret;
import com.example.demo.entities.Statu;
import com.example.demo.entities.Subvention;
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

    public Demande_Pret updateDemande_Pret(Long id, Demande_Pret p) {


        Demande_Pret existingDemande_Pret = dmd.findById(id)


                .orElseThrow();


        existingDemande_Pret.setMontant_net(p.getMontant_net());
        existingDemande_Pret.setMontantParEcheance(p.getMontantParEcheance());
        existingDemande_Pret.setType_pret(p.getType_pret());
        existingDemande_Pret.setNombreEcheances(p.getNombreEcheances());
        existingDemande_Pret.setDate_decaissement_pref(p.getDate_decaissement_pref());
        existingDemande_Pret.setDate_remb_pref(p.getDate_remb_pref());
        existingDemande_Pret.setType_plan(p.getType_plan());




        return dmd.save(existingDemande_Pret);
    }
}
