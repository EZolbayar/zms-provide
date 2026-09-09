package com.example.terguun.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long accountId;
    private Long branchId;
    private String productId;
    private Long clientId;
    private String accountType;
    private String accountStatus;
    private LocalDateTime statusDate;
    private LocalDateTime openDate;
    private BigDecimal balance;
    private BigDecimal frozenAmount;
    private BigDecimal interestRate;
    private BigDecimal excessRate;
    private Integer term;
    private LocalDateTime matureDate;
    private String contractId;
    private Integer numOwners;
    private String glPointerId;
    private Integer installmentTypeId;
    private BigDecimal appliedAmount;
    private LocalDateTime sanctionDate;
    private String sanctionInfo;
    private BigDecimal grantedAmount;
    private String grantedBy;
    private BigDecimal disbursedAmount;
    private LocalDateTime disbursedDate;
    private BigDecimal cumulativePenPaid;
    private BigDecimal cumulativeIntPaid;
    private BigDecimal cumulativePriPaid;
    private BigDecimal overduePrincipal;
    private Integer overdueDays;
    private BigDecimal accruedPenalty;
    private BigDecimal accruedInterest;
    private LocalDateTime intEffDate;
    private BigDecimal interestBalance;
    private BigDecimal penaltyBalance;
    private LocalDateTime lastTrxDate;
    private String lastSerialId;
    private String closeReasonId;
    private Boolean isLocked;
    private String lockedBy;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
