package com.example.terguun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.terguun.model.Collateral;

public interface CollateralRepository extends JpaRepository<Collateral, Long> {
}
