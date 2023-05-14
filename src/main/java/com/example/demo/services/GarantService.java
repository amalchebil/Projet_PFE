package com.example.demo.services;


import com.example.demo.entities.Garant;

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
}
