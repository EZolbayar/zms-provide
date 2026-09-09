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
@Table(name = "TBLOANINSTALLMENTS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoanInstallment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSTALLMENTID")
    private Long installmentId;

    @Column(name = "BRANCHID")
    private Long branchId;

    @Column(name = "ACCOUNTID")
    private Long accountId;

    @Column(name = "ISACTIVE")
    private Boolean isActive;

    @Column(name = "DUEDATE")
    private LocalDateTime dueDate;

    @Column(name = "PRINCIPAL")
    private BigDecimal principal;

    @Column(name = "INTEREST")
    private BigDecimal interest;

    @Column(name = "ORIGINALPRINCIPAL")
    private BigDecimal originalPrincipal;

    @Column(name = "AFTERBALANCE")
    private BigDecimal afterBalance;

    @Column(name = "BEFOREBALANCE")
    private BigDecimal beforeBalance;

    @Column(name = "INSTALLMENTFLAG")
    private String installmentFlag;

    @Column(name = "CREATEDON")
    private LocalDateTime createdOn;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "MODIFIEDON")
    private LocalDateTime modifiedOn;

    @Column(name = "MODIFIEDBY")
    private String modifiedBy;
}