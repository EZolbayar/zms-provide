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
public class CollateralDetailDto {

    private Long serialId;
    private Long branchId;
    private Long clientId;
    private Long collateralTypeId;
    private String collateralName;
    private String registrationId;
    private String licenseId;
    private String registrationOrgName;
    private String size;
    private String location;
    private String description;
    private LocalDateTime makeDate;
    private Boolean coFounder;
    private String coFoundersName;
    private String manufacture;
    private String shaftId;
    private String dedication;
    private String mark;
    private String color;
    private String valuer;
    private LocalDateTime valuedDate;
    private BigDecimal collateralValue;
    private BigDecimal marketValue;
    private BigDecimal forcedSaleValue;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
