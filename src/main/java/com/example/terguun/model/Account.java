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
@Data

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "TBACCOUNTS")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNTID")
    private String accountId;

    @Column(name = "BRANCHID")
    private String branchId;

    @Column(name = "PRODUCTID")
    private String productId;

    @Column(name = "CLIENTID")
    private String clientId;

    @Column(name = "ACCOUNTTYPE")
    private String accountType;

    @Column(name = "ACCOUNTSTATUS")
    private String accountStatus;

    @Column(name = "STATUSDATE")
    private LocalDateTime statusDate;

    @Column(name = "OPENDATE")
    private LocalDateTime openDate;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "FROZENAMOUNT")
    private BigDecimal frozenAmount;

    @Column(name = "INTERESTRATE")
    private BigDecimal interestRate;

    @Column(name = "EXCESSRATE")
    private BigDecimal excessRate;

    @Column(name = "TERM")
    private Integer term;

    @Column(name = "MATUREDATE")
    private LocalDateTime matureDate;

    @Column(name = "CONTRACTID")
    private String contractId;

    @Column(name = "NUMOWNERS")
    private Integer numOwners;

    @Column(name = "GLPOINTERID")
    private String glPointerId;

    @Column(name = "INSTALLMENTTYPEID")
    private Integer installmentTypeId;

    @Column(name = "APPLIEDAMOUNT")
    private BigDecimal appliedAmount;

    @Column(name = "SANCTIONDATE")
    private LocalDateTime sanctionDate;

    @Column(name = "SANCTIONINFO")
    private String sanctionInfo;

    @Column(name = "GRANTEDAMOUNT")
    private BigDecimal grantedAmount;

    @Column(name = "GRANTEDBY")
    private String grantedBy;

    @Column(name = "DISBURSEDAMOUNT")
    private BigDecimal disbursedAmount;

    @Column(name = "DISBURSEDDATE")
    private LocalDateTime disbursedDate;

    @Column(name = "CUMULATIVEPENPAID")
    private BigDecimal cumulativePenPaid;

    @Column(name = "CUMULATIVEINTPAID")
    private BigDecimal cumulativeIntPaid;

    @Column(name = "CUMULATIVEPRIPAID")
    private BigDecimal cumulativePriPaid;

    @Column(name = "OVERDUEPRINCIPAL")
    private BigDecimal overduePrincipal;

    @Column(name = "OVERDUEDAYS")
    private Integer overdueDays;

    @Column(name = "ACCRUEDPENALTY")
    private BigDecimal accruedPenalty;

    @Column(name = "ACCRUEDINTEREST")
    private BigDecimal accruedInterest;

    @Column(name = "INTEFFDATE")
    private LocalDateTime intEffDate;

    @Column(name = "INTERESTBALANCE")
    private BigDecimal interestBalance;

    @Column(name = "PENALTYBALANCE")
    private BigDecimal penaltyBalance;

    @Column(name = "LASTTRXDATE")
    private LocalDateTime lastTrxDate;

    @Column(name = "LASTSERIALID")
    private String lastSerialId;

    @Column(name = "CLOSEREASONID")
    private Integer closeReasonId;

    @Column(name = "ISLOCKED")
    private Boolean isLocked;

    @Column(name = "LOCKEDBY")
    private String lockedBy;

    @Column(name = "CREATEDON")
    private LocalDateTime createdOn;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "MODIFIEDON")
    private LocalDateTime modifiedOn;

    @Column(name = "MODIFIEDBY")
    private String modifiedBy;
}