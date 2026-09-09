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
@Table(name = "TBCOLLATERALS")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Collateral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLLATERALID")
    private Long collateralId;

    @Column(name = "COLLATERALNAME")
    private String collateralName;

    @Column(name = "RATEOFDEPRECIATION")
    private BigDecimal rateOfDepreciation;

    @Column(name = "DEPRECIATEEVERY")
    private Integer depreciateEvery;

    @Column(name = "ISTANGIBLE")
    private Boolean isTangible;

    @Column(name = "CREATEDON")
    private LocalDateTime createdOn;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "MODIFIEDON")
    private LocalDateTime modifiedOn;

    @Column(name = "MODIFIEDBY")
    private String modifiedBy;
}