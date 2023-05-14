package com.example.demo.controller;

import com.example.demo.entities.Client;
import com.example.demo.entities.Fichier;
import com.example.demo.entities.Garant;
import com.example.demo.repository.FichierRepository;
import com.example.demo.services.ClientService;
import com.example.demo.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.http.MediaType;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/Client")

public class ClientController {
    @Autowired
    private ClientService cltS ;
    @Autowired
    FichierRepository fichierRepository;
    @Autowired
    private EmailService emailService;

    @PostMapping(value = {"/ADDfichier/{id}"}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addFichier ( @RequestPart("fichier") MultipartFile file,@PathVariable Long id) {

        Client f= cltS.getClientById(id);
        try{

            Fichier fichier= new Fichier(file.getOriginalFilename(), file.getContentType(), file.getBytes(), f);

            fichierRepository.save(fichier);
            return "fichier ajouter";
        }
        catch(Exception e){
         System.out.println(e.getMessage());
         return null;
        }

    }

    @PostMapping("/ADDClient")
    public Client addclient (@Validated @RequestBody @Valid Client f) {




        return cltS.AjouterClient(f);
    }



  /*  public Set uploadfichier (MultipartFile[] multipartFiles, Long  id) throws IOException{
        Set<Fichier> fichiers = new HashSet<>();
        Client f= cltS.getClientById(id);
        for (MultipartFile file:multipartFiles){
            Fichier fichier= new Fichier(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes(),
                    f


            );


            fichiers.add(fichierRepository.save(fichier));


        }
        return fichiers;
    }*/

    @PostMapping("/UPDATEClient")
    public Client UpdateClient (@Validated @RequestBody @Valid Client f) {
        return cltS.ModifierClient(f);
    }

    @DeleteMapping("/DELETEClient/{id}")
    public void DeleteClient(@PathVariable Long id) {
        cltS.SupprimerClient(id);
    }

    @GetMapping("/GETALLClient")
    public List<Client> GETALLClient(){
        return cltS.ListClient();
    }

    @GetMapping("/GETCLIENTBYID/{id}")
    public Client GetClientById(@PathVariable Long id){
        return cltS.getClientById(id);
    }



}
 /* try{  emailService.sendEmail( f.getEmail_c(), "Nouveau client ajouté", "Bonjour " + f.getNom_c() + f.getPrenom_c()+
               ",\n\nNous avons le plaisir de vous avoir parmit nos clients." +
               "\nvoici vos information de connexion." +
               "\n email :"+f.getEmail_c()+
               "\n mot de passe :"+f.getCin_c()+
               "\n \nBienvenue.");}
        catch (MessagingException e) {

            e.printStackTrace();
        }*/