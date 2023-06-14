package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
public class TransfertService {
    @Autowired
    private AgenceService agenceService; // Injection de dépendance du service Agence
    @Autowired
    private EmailService emailService;
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
    ProjetService projetService;
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    private ClientRepository clt;
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
    public ResponseEntity<String> transfertPret(long clientId, double montant, long pretId) {
        Pret pret = pretService.getPretById(pretId);
        var demande = pret.getDemandePret();
        Client client = clientService.getClientById(clientId);
        var c = client.getAgc();
        EventCaisse eventCaisse = new EventCaisse();
        EventClient eventClient = new EventClient();
        if (c.getCaisse() >= montant) {
            c.setCaisse(c.getCaisse() - montant);
            client.setMontant_emprunte(client.getMontant_emprunte() + montant);
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
            return ResponseEntity.status(HttpStatus.OK)
                    .body("success");
        } else {
            System.out.println("montant non disponible dans la caisse");
            try {
                emailService.sendEmail("chebilamal50@gmail.com", "Demande Alimentation caisse",
                        "Bonjour " +

                                "\nNous avons besoins de liquidité pour l'agence: " + c.getNom_agence() +
                                "\n pour décaisser un pret de montant :" + montant +

                                "\n \nCordialement");
            } catch (MessagingException e) {

                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("montant non disponible dans la caisse");
        }

    }

    @Transactional
    public ResponseEntity<String> transfertSub(long clientId, double montant, long id_sub) throws MessagingException {
        Subvention sub = subventionService.getSubById(id_sub);

        Client client = clientService.getClientById(clientId);
        var c = client.getAgc();
        var demandeSub = sub.getDemandeSubvention();
        EventCaisse eventCaisse = new EventCaisse();
        EventClient eventClient = new EventClient();

        if (c.getCaisse() >= montant) {
            c.setCaisse(c.getCaisse() - montant);
            client.setMontant_sub(client.getMontant_sub() + montant);

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
            return ResponseEntity.status(HttpStatus.OK)
                    .body("succes");
        } else {
            System.out.println("montant non disponible dans la caisse");

            emailService.sendEmail("chebilamal50@gmail.com", "Demande Alimentation caisse",
                    "Bonjour " +

                            "\nNous avons besoins de liquidité pour l'agence: " + c.getNom_agence() +
                            "\n pour décaisser une subvention de montant :" + montant +

                            "\n \nCordialement");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("montant non disponible dans la caisse");
        }

    }

    @Autowired
    Demande_projetService demandeProjetService;

    @Transactional
    public ResponseEntity<String> transfertProjet(long demandeid) throws MessagingException {
        EventCaisse eventCaisse = new EventCaisse();
        EventClient eventClient = new EventClient();

        Demande_Projet demandeProjet = demandeProjetService.getDemande_ProjetById(demandeid);
        Projet projet = demandeProjet.getProjet();
        Client client = demandeProjet.getClient();
        var montant = projet.getMontant();
        var c = client.getAgc();

        if (c.getCaisse() >= montant) {
            c.setCaisse(c.getCaisse() - montant);
            client.setMontant_projet(client.getMontant_projet() + montant);
            //enregistrement de l'event caisse
            eventCaisse.setDate_event(LocalDateTime.now());
            eventCaisse.setMontant_event(montant);
            eventCaisse.setAgence2(c);
            eventCaisse.setClient1(client);
            eventCaisse.setType_event(TypeEvent.Retrait);
            //enregistrement de l'event client
            eventClient.setDate_event(LocalDateTime.now());
            eventClient.setMontant_event(montant);
            eventClient.setClient2(client);
            eventClient.setAgence1(c);
            eventClient.setType_event(TypeEvent.Versement);
            projet.setMontant(0);

            projet.setStatuP(Statu_p.Decaisser);
            demandeProjet.setStatus(Statu.Archiver);
            projet.setClient(client);
            agr.save(c);
            clt.save(client);
            projetRepository.save(projet);
            eventCaisseRepository.save(eventCaisse);
            eventClientRepository.save(eventClient);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("succes");
        } else {
            System.out.println("montant non disponible dans la caisse");
            emailService.sendEmail("chebilamal50@gmail.com", "Demande Alimentation caisse",
                    "Bonjour " +

                            "\nNous avons besoins de liquidité pour l'agence: " + c.getNom_agence() +
                            "\n pour décaisser un projet de montant :" + montant +

                            "\n \nCordialement");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("montant non disponible dans la caisse");
        }

    }


    @Transactional
    public void rembourserPret(Long pretId, double montant) {
        EventCaisse eventCaisse = new EventCaisse();
        EventClient eventClient = new EventClient();
        Pret pret = pretService.getPretById(pretId);
        var c = pret.getClient();
        var a = c.getAgc();
        c.setMontant_emprunte(c.getMontant_emprunte() - montant);
        pret.setMontant_p(pret.getMontant_p() - montant);
        a.setCaisse(a.getCaisse() + montant);

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

        if (pret.getMontant_p() == 0) {
            pret.setStatuP(Statu_p.Rembourser);
        }

        agr.save(a);
        clt.save(c);
        pretRepository.save(pret);
        eventCaisseRepository.save(eventCaisse);
        eventClientRepository.save(eventClient);

    }

    @Transactional
    public void rembourserProjet(Long projetId, double montant) {
        EventCaisse eventCaisse = new EventCaisse();
        EventClient eventClient = new EventClient();
        Projet projet = projetService.getProjetById(projetId);
        Client c = projet.getClient();
        var a = c.getAgc();


        c.setMontant_projet(c.getMontant_projet() - montant);
        projet.setMontant(projet.getMontant() + montant);
        a.setCaisse(a.getCaisse() + montant);

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

        if (c.getMontant_projet() == 0) {
            projet.setStatuP(Statu_p.Rembourser);
        }

        agr.save(a);
        clt.save(c);
        projetRepository.save(projet);
        eventCaisseRepository.save(eventCaisse);
        eventClientRepository.save(eventClient);

    }


    @Transactional
    public ResponseEntity<String> alimenterCaisse(Long agenceid, Double montant, Long centralid) {

        EventCaisse eventCaisse1 = new EventCaisse();
        EventCaisse eventCaisse = new EventCaisse();
        Agence agence = agenceService.getAgenceById(agenceid);
        Agence central = agenceService.getAgenceById(centralid);
        if (central.getCaisse() >= montant) {
            agence.setCaisse(agence.getCaisse() + montant);
            central.setCaisse(central.getCaisse() - montant);
            eventCaisse.setDate_event(LocalDateTime.now());
            eventCaisse.setMontant_event(montant);
            eventCaisse.setAgence2(agence);
            eventCaisse.setType_event(TypeEvent.Alimentation);


            eventCaisse1.setDate_event(LocalDateTime.now());
            eventCaisse1.setMontant_event(montant);
            eventCaisse1.setAgence2(central);

            eventCaisse1.setType_event(TypeEvent.Retrait);
            eventCaisseRepository.save(eventCaisse);
            agr.save(agence);
            agr.save(central);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("succes");
        } else {
            System.out.println("montant  non  disponible!!");

            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("montant non disponible dans la caisse");
        }

    }


}
