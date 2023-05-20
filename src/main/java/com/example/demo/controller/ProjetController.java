package com.example.demo.controller;

import com.example.demo.entities.Projet;
import com.example.demo.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/PROJET")
@RestController
@CrossOrigin(origins="http://localhost:3000")
public class ProjetController {
    @Autowired
    private ProjetService psrv;
    @GetMapping("/GETALLPROJETS")
    public List<Projet> getProjets() {
        return psrv.getProjets();
    }
    @GetMapping("/projet/{id}")
    public Optional<Projet> getProjet(@PathVariable long id){
        return psrv.afficherProjet(id);
    }
    @DeleteMapping("/DELETEPROJET/{id}")
    public void deleteProjet(@PathVariable long id){
        psrv.deleteProjet(id);
    }
    @PostMapping("/POSTPROJET")
    public void addProjet(@RequestBody Projet projet){
        psrv.addProjet(projet);
    }
    @PostMapping("/UPDATEPROJET")
    public void updateProjet(@RequestBody Projet projet){
        psrv.updateProjet(projet);
    }


    @PutMapping("/put/{id}")


    public ResponseEntity<Projet> updateProjet(@PathVariable Long id, @RequestBody Projet updatedprojet) {


        Projet Projet = psrv.updateproj(id,updatedprojet);


        return ResponseEntity.ok(Projet);


    }

}
