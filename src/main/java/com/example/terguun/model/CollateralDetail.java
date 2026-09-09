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
@Table(name = "TBCOLLATERALDETAILS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CollateralDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERIALID")
    private Long serialId;

    @Column(name = "BRANCHID")
    private Long branchId;

    @Column(name = "CLIENTID")
    private Long clientId;

    @Column(name = "COLLATERALTYPEID")
    private Long collateralTypeId;

    @Column(name = "COLLATERALNAME")
    private String collateralName;

    @Column(name = "REGISTRATIONID")
    private String registrationId;

    @Column(name = "LICENSEID")
    private String licenseId;

    @Column(name = "REGISTRATIONORGNAME")
    private String registrationOrgName;

    @Column(name = "SIZE")
    private String size;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MAKEDATE")
    private LocalDateTime makeDate;

    @Column(name = "COFOUNDER")
    private Boolean coFounder;

    @Column(name = "COFOUNDERSNAME")
    private String coFoundersName;

    @Column(name = "MANUFACTURE")
    private String manufacture;

    @Column(name = "SHAFTID")
    private String shaftId;

    @Column(name = "DEDICATION")
    private String dedication;

    @Column(name = "MARK")
    private String mark;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "VALUER")
    private String valuer;

    @Column(name = "VALUEDDATE")
    private LocalDateTime valuedDate;

    @Column(name = "COLLATERALVALUE")
    private BigDecimal collateralValue;

    @Column(name = "MARKETVALUE")
    private BigDecimal marketValue;

    @Column(name = "FORCEDSALEVALUE")
    private BigDecimal forcedSaleValue;

    @Column(name = "CREATEDON")
    private LocalDateTime createdOn;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "MODIFIEDON")
    private LocalDateTime modifiedOn;

    @Column(name = "MODIFIEDBY")
    private String modifiedBy;
}