package com.example.demo.controller;

import com.example.demo.entities.Garant;
import com.example.demo.services.GarantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/GARANT")

public class GarantController {
    @Autowired
    private GarantService cltS ;


    @PostMapping("/ADDGarant")

    public Garant addGarant (@Validated @RequestBody @Valid Garant f) {
        return cltS.AjouterGarant(f);
    }

    @PostMapping("/UPDATEGarant")

    public Garant UpdateGarant (@Validated @RequestBody @Valid Garant f) {
        return cltS.ModifierGarant(f);
    }

    @DeleteMapping("/DELETEGarant/{id}")

    public void DeleteGarant(@PathVariable Long id) {
        cltS.SupprimerGarant(id);
    }

    @GetMapping("/GETALLGarant")

    public List<Garant> GETALLGarant(){
        return cltS.ListGarant();
    }

    @GetMapping("/GETGarantBYID/{id}")

    public Optional<Garant> GetGarantById(@PathVariable Long id){
        return cltS.AfficherGarant(id);
    }
}
