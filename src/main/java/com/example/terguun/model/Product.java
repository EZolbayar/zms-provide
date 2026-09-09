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
@Table(name = "TBPRODUCTS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCTID")
    private Long productId;

    @Column(name = "BRANCHID")
    private Long branchId;

    @Column(name = "PRODUCTNAME")
    private String productName;

    @Column(name = "SHORTNAME")
    private String shortName;

    @Column(name = "CURRENCYID")
    private Integer currencyId;

    @Column(name = "ACCOUNTPREFIX")
    private String accountPrefix;

    @Column(name = "MINTERM")
    private Integer minTerm;

    @Column(name = "MAXTERM")
    private Integer maxTerm;

    @Column(name = "MINBALANCE")
    private BigDecimal minBalance;

    @Column(name = "MAXBALANCE")
    private BigDecimal maxBalance;

    @Column(name = "ACTACCOUNTSPERCLIENT")
    private Integer actAccountsPerClient;

    @Column(name = "ALLOWDEBITTRX")
    private Boolean allowDebitTrx;

    @Column(name = "ALLOWCREDITTRX")
    private Boolean allowCreditTrx;

    @Column(name = "NEWOPENINGALLOWED")
    private Boolean newOpeningAllowed;

    @Column(name = "DORMANTDAYS")
    private Integer dormantDays;

    @Column(name = "ISDEBITBALANCE")
    private Boolean isDebitBalance;

    @Column(name = "ISLOAN")
    private Boolean isLoan;

    @Column(name = "PENALTYRATE")
    private BigDecimal penaltyRate;

    @Column(name = "PENMETHODID")
    private Integer penMethodId;

    @Column(name = "ISTIMEDEPOSIT")
    private Boolean isTimeDeposit;

    @Column(name = "EARLYTERMINATIONRATE")
    private BigDecimal earlyTerminationRate;

    @Column(name = "ISINTWITHDRAWABLE")
    private Boolean isIntWithdrawable;

    @Column(name = "INTERESTMETHODID")
    private Integer interestMethodId;

    @Column(name = "RATEDIVISOR")
    private Integer rateDivisor;

    @Column(name = "INTERESTFREQUENCYID")
    private Integer interestFrequencyId;

    @Column(name = "COMPFREQUENCYID")
    private Integer compFrequencyId;

    @Column(name = "CONTROLGL")
    private String controlGL;

    @Column(name = "INTERESTPAYABLEGL")
    private String interestPayableGL;

    @Column(name = "INTERESTEXPENSEGL")
    private String interestExpenseGL;

    @Column(name = "DORMANTACCOUNTGL")
    private String dormantAccountGL;

    @Column(name = "CREATEDON")
    private LocalDateTime createdOn;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "MODIFIEDON")
    private LocalDateTime modifiedOn;

    @Column(name = "MODIFIEDBY")
    private String modifiedBy;
}