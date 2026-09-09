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
public class LoanInstallmentDto {

    private Long installmentId;
    private Long branchId;
    private Long accountId;
    private Boolean isActive;
    private LocalDateTime dueDate;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal originalPrincipal;
    private BigDecimal afterBalance;
    private BigDecimal beforeBalance;
    private String installmentFlag;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
