package com.example.demo.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    ADMIN (Set.of(
            Permission.ADMIN_DELETE,
            Permission.ADMIN_CREATE,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_READ
    )),
    PERSONNEL_AGENCE(Set.of(
            Permission.PERSONNEL_AGENCE_DELETE,
            Permission.PERSONNEL_AGENCE_UPDATE,
            Permission.PERSONNEL_AGENCE_CREATE,
            Permission.PERSONNEL_AGENCE_READ
    )),
    PERSONNEL_CENTRAL(Set.of(
            Permission.PERSONNEL_CENTRAL_DELETE,
            Permission.PERSONNEL_CENTRAL_UPDATE,
            Permission.PERSONNEL_CENTRAL_CREATE,
            Permission.PERSONNEL_CENTRAL_READ
    )),
    SUPER_ADMIN(Set.of(
            Permission.SUPER_ADMIN_UPDATE,
            Permission.SUPER_ADMIN_CREATE,
            Permission.SUPER_ADMIN_DELETE,
            Permission.SUPER_ADMIN_READ
    )),

    DIRECTEUR(Set.of(
            Permission.DIRECTEUR_CREATE,
            Permission.DIRECTEUR_DELETE,
            Permission.DIRECTEUR_UPDATE,
            Permission.DIRECTEUR_READ

    ))


    ;




@Getter
    private final Set<Permission>permissions;


public List<SimpleGrantedAuthority> getAuthorities(){
   var  authorities= getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
           .collect(Collectors.toList());

   authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
   return authorities;
}
}
