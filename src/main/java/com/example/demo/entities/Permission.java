package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    SUPER_ADMIN_READ("super_admin:read"),
    SUPER_ADMIN_DELETE("super_admin:delete"),
    SUPER_ADMIN_CREATE  ("super_admin:create"),
    SUPER_ADMIN_UPDATE("super_admin:update"),

    PERSONNEL_CENTRAL_READ("personnel_central:read"),
    PERSONNEL_CENTRAL_DELETE("personnel_central:delete"),
    PERSONNEL_CENTRAL_CREATE  ("personnel_central:create"),
    PERSONNEL_CENTRAL_UPDATE("personnel_central:update"),
    PERSONNEL_AGENCE_READ("personnel_agence:read"),
    PERSONNEL_AGENCE_DELETE("personnel_agence:delete"),
    PERSONNEL_AGENCE_CREATE  ("personnel_agence:create"),
    PERSONNEL_AGENCE_UPDATE("personnel_agence:update"),

    DIRECTEUR_READ("directeur:read"),
    DIRECTEUR_CREATE("directeur:create"),
    DIRECTEUR_UPDATE("directeur:update"),
    DIRECTEUR_DELETE("directeur:delete");


    @Getter
    private final String permission;
}
