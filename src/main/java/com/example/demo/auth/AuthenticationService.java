package com.example.demo.auth;

import com.example.demo.config.JwtService;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.AgenceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.AgenceService;
import com.example.demo.token.Token;
import com.example.demo.token.TokenRepository;
import com.example.demo.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AgenceService agenceService;

    private  final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse authenticate(AuthentiquationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( request.getEmail(),
                        request.getPassword())

        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,jwtToken);
        AuthenticationResponse response = AuthenticationResponse.builder().Token(jwtToken)
                .role(user.getRole().name())
                .id_agence(user.getAgence().getId())
                .lastname(user.getLastname())
                .firstnam(user.getFirstname())
                .email(user.getEmail()).build();


        return response;

    }



    public AuthenticationResponse register(RegisterRequest request) {

        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .cin(request.getCin())
                .agence(agenceService.getAgenceById(request.getId_agence()))
                .build();
        var savedUser=  repository.save(user);

        var jwtToken=jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder().Token(jwtToken).build();

    }


    private void revokeAllUserTokens(User user){
        var validUserToken= tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);

        });
        tokenRepository.saveAll(validUserToken);
    }
    private void saveUserToken(User user, String jwtToken) {
        var token= Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

}
