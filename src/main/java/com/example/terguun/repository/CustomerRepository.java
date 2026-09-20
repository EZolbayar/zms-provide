package com.example.terguun.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.terguun.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByCivilId(String civilId);

    List<Customer> findAllByOrderByCreatedOnDesc();

    /**
     * CUSTOMERID нь TBCLIENTS.ClientID-тэй ижил тэг-падтай тоон текст ("00993" г.м) тул шинэ дугаарыг
     * гараар тооцно. WITH (UPDLOCK, HOLDLOCK) hint нь тухайн гүйлгээ дуустал давхардсан дугаар
     * зэрэгцэн үүсэхээс сэргийлж мөрийн түвшний түгжээ тавина.
     */
    @Query(value = "SELECT MAX(TRY_CAST(CUSTOMERID AS BIGINT)) FROM TBCUSTOMERS WITH (UPDLOCK, HOLDLOCK) "
            + "WHERE CUSTOMERID NOT LIKE '%[^0-9]%'", nativeQuery = true)
    Long findMaxNumericCustomerId();
}
