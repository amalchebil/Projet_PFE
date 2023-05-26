package com.example.demo.controller;

import com.example.demo.entities.Fichier;
import com.example.demo.entities.Garant;
import com.example.demo.services.FichierService;
import com.example.demo.services.GarantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/Fichier")

public class FichierController {
    @Autowired
    private FichierService cltS ;


    @PostMapping("/ADDFichier")

    public Fichier addFichier (@Validated @RequestBody @Valid Fichier f) {
        return cltS.AjouterFichier(f);
    }

    @PostMapping("/UPDATEFichier")

    public Fichier UpdateFichier (@Validated @RequestBody @Valid Fichier f) {
        return cltS.ModifierFichier(f);
    }

    @DeleteMapping("/DELETEFichier/{id}")

    public void DeleteFichier(@PathVariable Long id) {
        cltS.SupprimerFichier(id);
    }

    @GetMapping("/GETALLFichier")

    public List<Fichier> GETALLFichier(){
        return cltS.ListFichier();
    }

    @GetMapping("/GETFichierBYID/{id}")

    public Optional<Fichier> GetFichierById(@PathVariable Long id){
        return cltS.AfficherFichier(id);
    }

    @PutMapping("/put/{id}")


    public ResponseEntity<Fichier> updateFichier(@PathVariable Long id, @RequestBody Fichier updatedFichier) {


        Fichier Fichier = cltS.updateFichier(id,updatedFichier);


        return ResponseEntity.ok(Fichier);


    }

}

