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
public class ProductDto {

    private Long productId;
    private Long branchId;
    private String productName;
    private String shortName;
    private Integer currencyId;
    private String accountPrefix;
    private Integer minTerm;
    private Integer maxTerm;
    private BigDecimal minBalance;
    private BigDecimal maxBalance;
    private Integer actAccountsPerClient;
    private Boolean allowDebitTrx;
    private Boolean allowCreditTrx;
    private Boolean newOpeningAllowed;
    private Integer dormantDays;
    private Boolean isDebitBalance;
    private Boolean isLoan;
    private BigDecimal penaltyRate;
    private Integer penMethodId;
    private Boolean isTimeDeposit;
    private BigDecimal earlyTerminationRate;
    private Boolean isIntWithdrawable;
    private Integer interestMethodId;
    private Integer rateDivisor;
    private Integer interestFrequencyId;
    private Integer compFrequencyId;
    private String controlGL;
    private String interestPayableGL;
    private String interestExpenseGL;
    private String dormantAccountGL;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
