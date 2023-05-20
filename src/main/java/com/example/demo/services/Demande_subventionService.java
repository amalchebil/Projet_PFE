package com.example.demo.services;

import com.example.demo.entities.Demande_Subvention;
import com.example.demo.entities.Statu;
import com.example.demo.entities.Subvention;
import com.example.demo.repository.Demande_projetRepository;
import com.example.demo.repository.Demande_subventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Demande_subventionService {
    @Autowired
    private Demande_subventionRepository dmd ;

    @Transactional
    public Demande_Subvention AjouterDemande (Demande_Subvention f) {
        f.setDateAttribut(LocalDateTime.now());
        f.setStatus(Statu.En_attente);

        return dmd.save(f);
    }

    @Transactional
    public Demande_Subvention ModifierDemande (Demande_Subvention f){
        return dmd.save(f);
    }

    @Transactional
    public void SupprimerDemande (Long id) {
        Demande_Subvention f = dmd.getById(id);
        dmd.delete(f);
    }

    @Transactional
    public List<Demande_Subvention> ListDemande(){
        return dmd.findAll();
    }

    @Transactional
    public Optional<Demande_Subvention> AfficherDemandePret(Long  id){
        return dmd.findById(id);
    }


    public Demande_Subvention updateDemande_Subvention(Long id, Demande_Subvention p) {


        Demande_Subvention existingDemande_Subvention = dmd.findById(id)


                .orElseThrow();


        existingDemande_Subvention.setDescription(p.getDescription());
        existingDemande_Subvention.setMontant_net(p.getMontant_net());




        return dmd.save(existingDemande_Subvention);
    }
}
