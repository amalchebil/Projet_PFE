package com.example.demo.controller;

import com.example.demo.auth.AuthenticationResponse;
import com.example.demo.auth.AuthenticationService;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.entities.Agence;
import com.example.demo.entities.User;
import com.example.demo.services.EmailService;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/USER")
public class UserController {
    @Autowired
    private  AuthenticationService service;
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

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

    @PostMapping("/UPDATEUSER")
    public User UpdateUser (@Validated @RequestBody @Valid User f) {
        return userService.ModifierUser(f);
    }

    @DeleteMapping("/DELETEUSER/{id}")
    public void DeleteUser(@PathVariable Long id) {
        userService.SupprimerUser(id);
    }

    @GetMapping("/GETALLUSERS")
    public List<User> GETALLUsers(){
        return userService.ListUsers();
    }

    @GetMapping("/GETUSERBYID/{id}")
    public User GetUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
