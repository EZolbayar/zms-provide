package com.example.terguun.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBACCOUNTINTERESTS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLUMNID")
    private Long columnId;

    @Column(name = "BRANCHID")
    private Long branchId;

    @Column(name = "ACCOUNTID")
    private Long accountId;

    @Column(name = "EFFECTIVEDATE")
    private LocalDateTime effectiveDate;

    @Column(name = "INTERESTRATE")
    private BigDecimal interestRate;

    @Column(name = "CREATEDON")
    private LocalDateTime createdOn;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "BATCHID")
    private Long batchId;

    @Column(name = "ISPENDING")
    private Boolean isPending;
}