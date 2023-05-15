package com.example.demo.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String Token;
    private String email;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public Long getId_agence() {
        return id_agence;
    }

    public void setId_agence(Long id_agence) {
        this.id_agence = id_agence;
    }

    private Long id_agence;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
