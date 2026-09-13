package com.example.terguun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.terguun.model.LoanInstallment;

public interface LoanInstallmentRepository extends JpaRepository<LoanInstallment, Long> {

    List<LoanInstallment> findByAccountIdIn(List<String> accountIds);
}
