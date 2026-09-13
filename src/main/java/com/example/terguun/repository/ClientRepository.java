package com.example.terguun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.terguun.model.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
}
