package com.example.demo.repository;

import com.example.demo.entities.EventClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventClientRepository extends JpaRepository<EventClient,Long> {
}
