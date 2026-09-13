package com.example.terguun.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.terguun.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findByClientId(String clientId);

    List<Account> findByCreatedOnAfterOrModifiedOnAfter(LocalDateTime createdOn, LocalDateTime modifiedOn);
}
