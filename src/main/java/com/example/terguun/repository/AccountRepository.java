package com.example.terguun.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.terguun.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByClientId(Long clientId);

    List<Account> findByCreatedOnAfterOrModifiedOnAfter(LocalDateTime createdOn, LocalDateTime modifiedOn);
}
