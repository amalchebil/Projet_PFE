package com.example.demo.services;

import com.example.demo.entities.EventCaisse;

import com.example.demo.repository.EventCaisseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventCaisseService {
  @Autowired
    private EventCaisseRepository evt ;

    @Transactional
    public EventCaisse AjouterEvent (EventCaisse f) {
        return evt.save(f);
    }

    @Transactional
    public EventCaisse ModifierEvent (EventCaisse f){
        return evt.save(f);
    }

    @Transactional
    public void SupprimerEvent (Long id) {
        EventCaisse f = evt.getById(id);
        evt.delete(f);
    }

    @Transactional
    public List<EventCaisse> ListEvent(){
        return evt.findAll();
    }
    @Transactional
    public Optional<EventCaisse> AfficherEvent(Long  id){
        return evt.findById(id);
    }
}
