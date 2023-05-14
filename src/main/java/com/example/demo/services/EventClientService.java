package com.example.demo.services;

import com.example.demo.entities.EventClient;
import com.example.demo.repository.EventClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventClientService {
    @Autowired
    private EventClientRepository evt ;

    @Transactional
    public EventClient AjouterEvent (EventClient f) {
        return evt.save(f);
    }

    @Transactional
    public EventClient ModifierEvent (EventClient f){
        return evt.save(f);
    }

    @Transactional
    public void SupprimerEvent (Long id) {
        EventClient f = evt.getById(id);
        evt.delete(f);
    }

    @Transactional
    public List<EventClient> ListEvent(){
        return evt.findAll();
    }
    @Transactional
    public Optional<EventClient> AfficherEvent(Long  id){
        return evt.findById(id);
    }
}
