package com.example.demo.controller;

import com.example.demo.entities.Demande_Subvention;
import com.example.demo.entities.Statu;
import com.example.demo.repository.Demande_subventionRepository;
import com.example.demo.services.Demande_subventionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/DEMANDESUBBVENTION")
public class Demande_subventionController {
    @Autowired
    private Demande_subventionService cltS ;


    @PostMapping("/ADDDEMANDE")
    public Demande_Subvention addDemande (@Validated @RequestBody @Valid Demande_Subvention f) {
        return cltS.AjouterDemande(f);
    }

    @PostMapping("/UPDATEDEMANDE")

    public Demande_Subvention UpdateDemande (@Validated @RequestBody @Valid Demande_Subvention f) {
        return cltS.ModifierDemande(f);
    }

    @DeleteMapping("/DELETEDEMANDE/{id}")

    public void DeleteDemande(@PathVariable Long id) {
        cltS.SupprimerDemande(id);
    }

    @GetMapping("/GETALLDEMANDE")

    public List<Demande_Subvention> GETALLDemande(){
        return cltS.ListDemande();
    }


    @GetMapping("/GETDEMANDEBYID/{id}")

    public Optional<Demande_Subvention> GetDemandeById(@PathVariable Long id){
        return cltS.AfficherDemandePret(id);
    }

    @Autowired
    private Demande_subventionRepository dmd;
    //modification de la statut de la demande
    @PostMapping("{id}/modifier-statut")

    public String modifierStatutDemande(@PathVariable Long id, @RequestParam String newStatus) {
        // Récupérer la demande à modifier en fonction de l'id
        Demande_Subvention demande = dmd.findById(id)
                .orElseThrow(() -> new NotFoundException("Demande non trouvée"));

        // Modifier le statut de la demande en fonction de la nouvelle valeur soumise par l'utilisateur


        if (newStatus.equals("Approuver")){
            demande.setStatus(Statu.Approuver);
        }
        if (newStatus.equals("Rejeter")){
            demande.setStatus(Statu.Rejeter);
        }

        if (newStatus.equals("Archiver")){
            demande.setStatus(Statu.Archiver);
        }


        // Enregistrer les modifications dans la base de données
        dmd.save(demande);

        // Rediriger l'utilisateur vers la page de détails de la demande modifiée
        return "";
    }

}
