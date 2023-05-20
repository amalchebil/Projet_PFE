package com.example.demo.services;

import com.example.demo.entities.Pret;
import com.example.demo.entities.Projet;
import com.example.demo.entities.Statu_p;
import com.example.demo.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;
    @Transactional
    public List<Projet> getProjets() {
        return projetRepository.findAll();
    }

    @Transactional
    public Optional<Projet> afficherProjet(Long  id) {


        return projetRepository.findById(id);
    }
    @Transactional
    public void deleteProjet(long id) {
        projetRepository.deleteById(id);
    }
    @Transactional
    public void addProjet(Projet projet) {

        projet.setStatuP(Statu_p.En_cour);
        projetRepository.save(projet);
    }
    @Transactional
    public void updateProjet(Projet projet) {
        projetRepository.save(projet);
    }


    @Transactional


    public Projet updateproj(Long id,Projet p) {


        Projet existingprojet = projetRepository.findById(id)


                .orElseThrow();


        existingprojet.setSecteur(p.getSecteur());
        existingprojet.setMontant(p.getMontant());
        existingprojet.setMontantParEcheance(p.getMontantParEcheance());
        existingprojet.setMontantParTraite(p.getMontantParTraite());
        existingprojet.setDateTraitees(p.getDateTraitees());
        existingprojet.setDatePremiereEcheance(p.getDatePremiereEcheance());
        existingprojet.setGouvernorat(p.getGouvernorat());
        existingprojet.setActivite(p.getActivite());
        existingprojet.setDelegation(p.getDelegation());
        existingprojet.setNombreEcheances(p.getNombreEcheances());
        existingprojet.setNombreTraites(p.getNombreTraites());




        return projetRepository.save(existingprojet);
    }


    @Transactional
    public Projet getProjetById(long id) {
        Optional<Projet> projet = projetRepository.findById(id);
        if (projet.isPresent()) {
            return projet.get();
        } else {
            throw new NoSuchElementException("Projet introuvable pour l'ID: " + id);
        }
    }

}
