package com.example.terguun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.terguun.model.LoanInstallment;
import com.example.terguun.model.LoanInstallmentId;

public interface LoanInstallmentRepository extends JpaRepository<LoanInstallment, LoanInstallmentId> {

    List<LoanInstallment> findByAccountIdIn(List<String> accountIds);
}
