package com.example.demo.controller;

import com.example.demo.entities.Projet;
import com.example.demo.entities.Subvention;
import com.example.demo.services.SubventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/SUBVENTION")
@CrossOrigin(origins="http://localhost:3000")
public class SubventionController {
    @Autowired
    private SubventionService psrv;
    @GetMapping("/GETALLSubventionS")

    public List<Subvention> getSubventions() {
        return psrv.ListSubvention();
    }
    @GetMapping("/Subvention/{id}")

    public Optional<Subvention> getSubvention(@PathVariable long id){
        return psrv.AfficherSubvention(id);
    }
    @DeleteMapping("/DELETESubvention/{id}")

    public void deleteSubvention(@PathVariable long id){
        psrv.SupprimerSubvention(id);
    }
    @PostMapping("/POSTSubvention")

    public void addSubvention(@RequestBody Subvention Subvention){
        psrv.AjouterSubvention(Subvention);
    }
    @PostMapping("/UPDATESubvention")

    public void updateSubvention(@RequestBody Subvention Subvention){
        psrv.ModifierSubvention(Subvention);
    }

    @PutMapping("/put/{id}")


    public ResponseEntity<Subvention> updateSubvention(@PathVariable Long id, @RequestBody Subvention updatedSubvention) {


        Subvention Subvention = psrv.updateSubvention(id,updatedSubvention);


        return ResponseEntity.ok(Subvention);


    }


}
