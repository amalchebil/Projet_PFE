package com.example.demo.controller;

import com.example.demo.entities.EventClient;
import com.example.demo.services.EventClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/EVENTClient")
public class EventClientController {
    @Autowired
    private EventClientService cltS ;


    @PostMapping("/ADDEvent")
    public EventClient addEvent (@Validated @RequestBody @Valid EventClient f) {
        return cltS.AjouterEvent(f);
    }

    @PostMapping("/UPDATEEvent")
    public EventClient UpdateEvent (@Validated @RequestBody @Valid EventClient f) {
        return cltS.ModifierEvent(f);
    }

    @DeleteMapping("/DELETEEvent/{id}")
    public void DeleteEvent(@PathVariable Long id) {
        cltS.SupprimerEvent(id);
    }

    @GetMapping("/GETALLEvent")
    public List<EventClient> GETALLEvent(){
        return cltS.ListEvent();
    }

    @GetMapping("/GETEVENTBYID/{id}")
    public Optional<EventClient> GetEventById(@PathVariable Long id){
        return cltS.AfficherEvent(id);
    }
}
