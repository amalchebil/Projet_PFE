package com.example.demo.services;

import com.example.demo.entities.Agence;
import com.example.demo.entities.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clt ;

    @Transactional

    public Client updateClient(Long id, Client updatedClient) {

        Client existingClient = clt.findById(id)

                .orElseThrow();

        existingClient.setNom_c(updatedClient.getNom_c());

        existingClient.setPrenom_c(updatedClient.getPrenom_c());

        existingClient.setEmail_c(updatedClient.getEmail_c());

        existingClient.setNumtel_c(updatedClient.getNumtel_c());

        existingClient.setDate_naiss(updatedClient.getDate_naiss());

        existingClient.setAdresse_c(updatedClient.getAdresse_c());

        return clt.save(existingClient);

    }

    @Transactional
    public Client AjouterClient (Client f){

      f.setMontant_emprunte(0);
      f.setMontant_projet(0);
      f.setMontant_sub(0);
        return clt.save(f);
    }

    @Transactional
    public Client ModifierClient (Client f){
        return clt.save(f);
    }

    @Transactional
    public void SupprimerClient (Long id) {
        Client f = clt.getById(id);
        clt.delete(f);
    }

    @Transactional
    public List<Client> ListClient(){
        return clt.findAll();
    }

    @Transactional
    public Optional<Client> AfficherClient(Long  id) {
        return clt.findById(id);
    }

    @Transactional
    public Client getClientById(long id) {
        Optional<Client> client = clt.findById(id); // Récupère le client correspondante dans la base de données par son ID
        if (client.isPresent()) {
            return client.get(); // Retourne le client si elle existe
        } else {
            throw new NoSuchElementException("Client introuvable pour l'ID: " + id); // Lance une exception si le client n'existe pas
        }
    }
}
