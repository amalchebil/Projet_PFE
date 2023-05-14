package com.example.demo.controller;

import com.example.demo.entities.Pret;
import com.example.demo.services.PretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/PRET")
@CrossOrigin(origins="http://localhost:3000")
public class PretController {
    @Autowired
    private PretService psrv;
    @GetMapping("/GETALLPretS")

    public List<Pret> getPrets() {
        return psrv.getPrets();
    }
    @GetMapping("/Pret/{id}")

    public Optional<Pret> getPret(@PathVariable long id){
        return psrv.afficherPret(id);
    }
    @DeleteMapping("/DELETEPret/{id}")

    public void deletePret(@PathVariable long id){
        psrv.deletePret(id);
    }
    @PostMapping("/POSTPret")

    public void addPret(@RequestBody Pret Pret){
        psrv.addPret(Pret);
    }
    @PostMapping("/UPDATEPret")

    public void updatePret(@RequestBody Pret Pret){
        psrv.updatePret(Pret);
    }



}
