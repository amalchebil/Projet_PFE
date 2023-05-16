package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransfertService {
    @Autowired
    private AgenceService agenceService; // Injection de dépendance du service Agence

    @Autowired
    private ClientService clientService; // Injection de dépendance du service client
    @Autowired
    private SubventionService subventionService;
    @Autowired
    private SubventionRepository subventionRepository;
    @Autowired
    Demande_pretRepository demandePretRepository;
    @Autowired
    Demande_subventionRepository demandeSubventionRepository;

    @Autowired
    private ClientRepository clt ;
    @Autowired
    private AgenceRepository agr;
    @Autowired
    private PretRepository pretRepository;
    @Autowired
    private PretService pretService;
    @Autowired
  private EventCaisseRepository eventCaisseRepository;
//
  @Autowired
   private EventClientRepository eventClientRepository;

    @Transactional
    public void transfertPret(long clientId,double montant,long pretId){
         Pret pret= pretService.getPretById(pretId);
         var demande = pret.getDemandePret();
        Client client = clientService.getClientById(clientId);
        var c = client.getAgc();
        EventCaisse eventCaisse =new EventCaisse();
        EventClient eventClient=new EventClient();
        if(c.getCaisse()>=montant){
        c.setCaisse(c.getCaisse()-montant);
        client.setMontant_emprunte(client.getMontant_emprunte()+montant);
//enregistrement de l'event caisse
            eventCaisse.setDate_event(LocalDateTime.now());
            eventCaisse.setMontant_event(montant);
            eventCaisse.setAgence2(c);
            eventCaisse.setClient1(client);
            eventCaisse.setType_event(TypeEvent.Retrait);
//            //enregistrement de l'event client
            eventClient.setDate_event(LocalDateTime.now());
            eventClient.setMontant_event(montant);
            eventClient.setClient2(client);
            eventClient.setAgence1(c);
            eventClient.setType_event(TypeEvent.Versement);

            //status décaissé
            pret.setStatuP(Statu_p.Decaisser);
            demande.setStatus(Statu.Archiver);
           demandePretRepository.save(demande);
      agr.save(c);
      clt.save(client);
      pretRepository.save(pret);
        eventCaisseRepository.save(eventCaisse);
          eventClientRepository.save(eventClient);
          }
        else {
            System.out.println("montant non disponible dans la caisse");
        }

    }

    @Transactional
    public void transfertSub(long clientId,double montant,long id_sub){
        Subvention sub =subventionService.getSubById(id_sub);

        Client client = clientService.getClientById(clientId);
        var c = client.getAgc();
        var demandeSub= sub.getDemandeSubvention();
       EventCaisse eventCaisse =new EventCaisse();
        EventClient eventClient=new EventClient();

        if(c.getCaisse()>=montant){
            c.setCaisse(c.getCaisse()-montant);
            client.setMontant_sub(client.getMontant_sub()+montant);

        //enregistrement de l'event caisse
          eventCaisse.setDate_event(LocalDateTime.now());
          eventCaisse.setMontant_event(montant);
          eventCaisse.setAgence2(c);
          eventCaisse.setClient1(client);
          eventCaisse.setType_event(TypeEvent.Retrait);
//          //enregistrement de l'event client
            eventClient.setDate_event(LocalDateTime.now());
            eventClient.setMontant_event(montant);
            eventClient.setClient2(client);
            eventClient.setAgence1(c);
            eventClient.setType_event(TypeEvent.Versement);

            sub.setStatuS(Statu_p.Decaisser);
            demandeSub.setStatus(Statu.Archiver);
            subventionRepository.save(sub);
            demandeSubventionRepository.save(demandeSub);

            agr.save(c);
            clt.save(client);
      eventCaisseRepository.save(eventCaisse);
      eventClientRepository.save(eventClient);
        }
        else {
            System.out.println("montant non disponible dans la caisse");
        }

    }

    @Transactional
    public void transfertProjet(long clientId,double montant){
        EventCaisse eventCaisse =new EventCaisse();
        EventClient eventClient=new EventClient();

        Client client = clientService.getClientById(clientId);
        var c = client.getAgc();

        if(c.getCaisse()>=montant){
            c.setCaisse(c.getCaisse()-montant);
            client.setMontant_projet(client.getMontant_projet()+montant);
            //enregistrement de l'event caisse
            eventCaisse.setDate_event(LocalDateTime.now());
            eventCaisse.setMontant_event(montant);
            eventCaisse.setAgence2(c);
            eventCaisse.setClient1(client);
            eventCaisse.setType_event(TypeEvent.Retrait);
//            //enregistrement de l'event client
            eventClient.setDate_event(LocalDateTime.now());
            eventClient.setMontant_event(montant);
            eventClient.setClient2(client);
            eventClient.setAgence1(c);
            eventClient.setType_event(TypeEvent.Versement);


            agr.save(c);
            clt.save(client);
           eventCaisseRepository.save(eventCaisse);
          eventClientRepository.save(eventClient);
          }
        else {
            System.out.println("montant non disponible dans la caisse");
        }

    }



    @Transactional
    public void rembourserPret(Long pretId,double montant){
        EventCaisse eventCaisse =new EventCaisse();
        EventClient eventClient=new EventClient();
        Pret pret=pretService.getPretById(pretId);
        var c =pret.getClient();
        var a=c.getAgc();
        c.setMontant_emprunte(c.getMontant_emprunte()-montant);
        pret.setMontant_p(pret.getMontant_p()-montant);
        a.setCaisse(a.getCaisse()+montant);

        //enregistrement de l'event caisse
        eventCaisse.setDate_event(LocalDateTime.now());
        eventCaisse.setMontant_event(montant);
        eventCaisse.setAgence2(a);
        eventCaisse.setClient1(c);
        eventCaisse.setType_event(TypeEvent.Versement);
//        //enregistrement de l'event client
        eventClient.setDate_event(LocalDateTime.now());
        eventClient.setMontant_event(montant);
        eventClient.setClient2(c);
        eventClient.setAgence1(a);
        eventClient.setType_event(TypeEvent.Retrait);

        agr.save(a);
        clt.save(c);
        pretRepository.save(pret);
       eventCaisseRepository.save(eventCaisse);
    eventClientRepository.save(eventClient);

    }




}
