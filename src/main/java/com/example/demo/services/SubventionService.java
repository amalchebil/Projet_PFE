package com.example.demo.services;

import com.example.demo.entities.Pret;
import com.example.demo.entities.Subvention;
import com.example.demo.entities.Statu;

import com.example.demo.repository.SubventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SubventionService {
    @Autowired
    private SubventionRepository dmd ;

    @Transactional
    public Subvention AjouterSubvention (Subvention f) {
        f.setDate_creation(LocalDateTime.now());

        return dmd.save(f);
    }

    @Transactional
    public Subvention ModifierSubvention (Subvention f){
        return dmd.save(f);
    }

    @Transactional
    public void SupprimerSubvention (Long id) {
        Subvention f = dmd.getById(id);
        dmd.delete(f);
    }

    @Transactional
    public List<Subvention> ListSubvention(){
        return dmd.findAll();
    }

    @Transactional
    public Optional<Subvention> AfficherSubvention(Long  id){
        return dmd.findById(id);
    }

    @Transactional
    public Subvention getSubById(long id) {
        Optional<Subvention> sub = dmd.findById(id);
        if (sub.isPresent()) {
            return sub.get();
        } else {
            throw new NoSuchElementException("Subvention introuvable pour l'ID: " + id); // Lance une exception si le pret n'existe pas
        }
    }
}
