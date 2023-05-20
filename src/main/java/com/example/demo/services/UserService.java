package com.example.demo.services;

import com.example.demo.entities.Agence;
import com.example.demo.entities.Subvention;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User ModifierUser (User f){
        return userRepository.save(f);
    }

    @Transactional
    public void SupprimerUser (Long id) {
        User f = userRepository.getById(id);
        userRepository.delete(f);
    }

    @Transactional
    public List<User> ListUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> AfficherUser(Long  id) {
        return userRepository.findById(id);
    }
    @Transactional
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoSuchElementException("Agence introuvable pour l'ID: " + id);
        }
    }
    @Transactional
    public User updateUser(Long id, User p) {


        User existingUser = userRepository.findById(id)


                .orElseThrow();


        existingUser.setFirstname(p.getFirstname());
        existingUser.setLastname(p.getLastname());
        existingUser.setEmail(p.getEmail());
        existingUser.setRole(p.getRole());
        existingUser.setAgence(p.getAgence());




        return userRepository.save(existingUser);
    }
}
