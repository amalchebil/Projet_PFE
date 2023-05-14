package com.example.demo.controller;

import com.example.demo.auth.AuthenticationResponse;
import com.example.demo.auth.AuthenticationService;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/USER")
public class UserController {
    @Autowired
    private  AuthenticationService service;
    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){

        try{  emailService.sendEmail(request.getEmail(), "Nouveau Utilisateur ajouté", "Bonjour " + request.getFirstname() + request.getLastname()+
                ",\n\nNous avons le plaisir de vous avoir parmit notre équipe." +
                "\nvoici vos information de connexion." +
                "\n email :"+request.getEmail()+
                "\n mot de passe :"+request.getPassword()+
                "\n \nBienvenue.");}
        catch (MessagingException e) {

            e.printStackTrace();
        }
        return ResponseEntity.ok(service.register(request));

    }
}
