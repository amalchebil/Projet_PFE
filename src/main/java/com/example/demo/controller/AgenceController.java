package com.example.demo.controller;

import com.example.demo.entities.Agence;
import com.example.demo.repository.AgenceRepository;
import com.example.demo.services.AgenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/AGENCE")
public class AgenceController {
    @Autowired
    private AgenceService cltS ;

    @PostMapping("/Ajouteragence")
    public Agence ajouterAgence(@Validated @RequestBody @Valid Agence f){
        return cltS.AjouterAgence(f);
    }
    @PostMapping("/UPDATEAgence")
    public Agence UpdateAgence (@Validated @RequestBody @Valid Agence f) {
        return cltS.ModifierAgence(f);
    }

    @DeleteMapping("/DELETEAgence/{id}")
    public void DeleteAgence(@PathVariable Long id) {
        cltS.SupprimerAgence(id);
    }

    @GetMapping("/GETALLAgence")
    public List<Agence> GETALLAgence(){
        return cltS.ListAgence();
    }

    /*@GetMapping("/GETAgenceBYID/{id}")
    public Agence GetAgenceById(@PathVariable Long id){
        return cltS.getAgenceById(id);
    }*/
@Autowired
    AgenceRepository agenceRepository;
//Ajout d'un montant a la caisse
    //Alimentation de caisse
    @PostMapping("/{id}/ajouter-caisse")
    public ResponseEntity<Agence> ajouterCaisse(@PathVariable Long id, @RequestBody double montant) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Agence non trouvée"));

        agence.ajouterMontant(montant);
        Agence updatedAgence = agenceRepository.save(agence);

        return ResponseEntity.ok(updatedAgence);
    }
//sortit d'argent de la caisse
    @PostMapping("/{id}/soustraire-caisse")
    public ResponseEntity<Agence> soustraireCaisse(@PathVariable Long id, @RequestBody double montant) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Agence non trouvée"));

        agence.soustraireMontant(montant);
        Agence updatedAgence = agenceRepository.save(agence);

        return ResponseEntity.ok(updatedAgence);
    }


}






