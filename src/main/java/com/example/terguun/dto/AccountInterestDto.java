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
public class AccountInterestDto {

    private Long columnId;
    private Long branchId;
    private Long accountId;
    private LocalDateTime effectiveDate;
    private BigDecimal interestRate;
    private LocalDateTime createdOn;
    private String createdBy;
    private Long batchId;
    private Boolean isPending;
}
