package com.example.demo.services;

import com.example.demo.entities.Client;
import com.example.demo.entities.Pret;
import com.example.demo.entities.Statu_p;
import com.example.demo.entities.User;
import com.example.demo.repository.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;
    @Transactional
    public List<Pret> getPrets() {
        return pretRepository.findAll();
    }

    @Transactional
    public Optional<Pret> afficherPret(Long  id) {
        return pretRepository.findById(id);
    }
    @Transactional
    public void deletePret(long id) {
        pretRepository.deleteById(id);
    }
    @Transactional
    public void addPret(Pret Pret) {
        Pret.setDate_creation(LocalDateTime.now());
        Pret.setStatuP(Statu_p.En_cour);
                pretRepository.save(Pret);
    }
    @Transactional
    public void updatePret(Pret Pret) {
        pretRepository.save(Pret);
    }


    @Transactional
    public Pret getPretById(long id) {
        Optional<Pret> pret = pretRepository.findById(id);
        if (pret.isPresent()) {
            return pret.get();
        } else {
            throw new NoSuchElementException("Pret introuvable pour l'ID: " + id); // Lance une exception si le pret n'existe pas
        }
    }
    @Transactional
    public Pret updatePret(Long id, Pret p) {


        Pret existingPret = pretRepository.findById(id)


                .orElseThrow();


        existingPret.setMontant_p(p.getMontant_p());
        existingPret.setMontantParEcheance(p.getMontantParEcheance());
        existingPret.setDatePremiereEcheance(p.getDatePremiereEcheance());
        existingPret.setNombreEcheances(p.getNombreEcheances());
        existingPret.setGarant(p.getGarant());
        existingPret.setType_plan(p.getType_plan());
        existingPret.setTypepret(p.getTypepret());




        return pretRepository.save(existingPret);
    }
}
