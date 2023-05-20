package com.example.demo.services;


import com.example.demo.entities.Garant;

import com.example.demo.entities.User;
import com.example.demo.repository.GarantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GarantService {
    @Autowired
    private GarantRepository gar ;


    @Transactional
    public Garant AjouterGarant (Garant f){

        return gar.save(f);
    }

    @Transactional
    public Garant ModifierGarant (Garant f){
        return gar.save(f);
    }

    @Transactional
    public void SupprimerGarant (Long id) {
        Garant f = gar.getById(id);
        gar.delete(f);
    }

    @Transactional
    public List<Garant> ListGarant(){
        return gar.findAll();
    }

    @Transactional
    public Optional<Garant> AfficherGarant(Long  id) {
        return gar.findById(id);
    }
    @Transactional
    public Garant updateGarant(Long id, Garant p) {


        Garant existingGarant = gar.findById(id)


                .orElseThrow();


        existingGarant.setNom_g(p.getNom_g());
        existingGarant.setPrenom_g(p.getPrenom_g());
        existingGarant.setDescrip_g(p.getDescrip_g());
        existingGarant.setRelation_g(p.getRelation_g());
        existingGarant.setCin_g(p.getCin_g());




        return gar.save(existingGarant);
    }
}
