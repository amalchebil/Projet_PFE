package com.example.demo.auth;

import com.example.demo.entities.Agence;
import com.example.demo.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private Role role;

    private String cin;

    private Long id_agence;

}
