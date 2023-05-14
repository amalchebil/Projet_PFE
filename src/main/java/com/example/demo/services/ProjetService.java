package com.example.demo.services;

import com.example.demo.entities.Projet;
import com.example.demo.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        projetRepository.save(projet);
    }
    @Transactional
    public void updateProjet(Projet projet) {
        projetRepository.save(projet);
    }

}
