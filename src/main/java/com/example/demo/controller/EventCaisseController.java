package com.example.demo.controller;


import com.example.demo.entities.EventCaisse;

import com.example.demo.services.EventCaisseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/EVENTCaisse")
public class EventCaisseController {
    @Autowired
    private EventCaisseService cltS ;


    @PostMapping("/ADDEvent")
    public EventCaisse addEvent (@Validated @RequestBody @Valid EventCaisse f) {
        return cltS.AjouterEvent(f);
    }

    @PostMapping("/UPDATEEvent")
    public EventCaisse UpdateEvent (@Validated @RequestBody @Valid EventCaisse f) {
        return cltS.ModifierEvent(f);
    }

    @DeleteMapping("/DELETEEvent/{id}")
    public void DeleteEvent(@PathVariable Long id) {
        cltS.SupprimerEvent(id);
    }

    @GetMapping("/GETALLEvent")
    public List<EventCaisse> GETALLEvent(){
        return cltS.ListEvent();
    }

    @GetMapping("/GETEVENTBYID/{id}")
    public Optional<EventCaisse> GetEventById(@PathVariable Long id){
        return cltS.AfficherEvent(id);
    }
}
