package com.example.backtest.repository;

import com.example.backtest.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
    Optional<Clients> findByDocumentNumber(String documentNumber);
}