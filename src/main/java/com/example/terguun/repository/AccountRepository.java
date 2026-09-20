package com.example.terguun.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.terguun.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findByClientId(String clientId);

    /** Олон харилцагчийн дансыг нэг асуулгаар татна (N+1-ээс сэргийлнэ). */
    List<Account> findByClientIdIn(Collection<String> clientIds);

    /** Шинэ данс: createdOn нь өгөгдсөн цагаас хойш. */
    List<Account> findByCreatedOnAfter(LocalDateTime createdOn);

    /** Хаагдсан данс: modifiedOn нь өгөгдсөн цагаас хойш, төлөв нь өгөгдсөн утга (хаагдсан = "C") бөгөөд үлдэгдэл нь өгөгдсөн утгатай (0). */
    List<Account> findByModifiedOnAfterAndAccountStatusAndBalance(LocalDateTime modifiedOn, String accountStatus,
            BigDecimal balance);

    /**
     * Эргэн төлөлт хийгдсэн данс: modifiedOn нь өгөгдсөн цагаас хойш, данс нээгдсэнийхээ дараа өөрчлөгдсөн
     * (modifiedOn > createdOn), төлөв нь идэвхтэй бөгөөд үлдэгдэл үлдсэн. Зээл олгосон үеийн анхны бичилт
     * (modifiedOn = createdOn) болон бүрэн хаагдсан зээл энд орохгүй.
     */
    @Query("SELECT a FROM Account a WHERE a.modifiedOn > :since AND a.modifiedOn > a.createdOn "
            + "AND a.accountStatus = :accountStatus AND a.balance > 0")
    List<Account> findRepaidAccounts(@Param("since") LocalDateTime since,
            @Param("accountStatus") String accountStatus);
}
