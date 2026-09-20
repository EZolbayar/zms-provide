package com.example.terguun.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBLOANINSTALLMENTS")
@IdClass(LoanInstallmentId.class)
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoanInstallment {

    // INSTALLMENTID нь 1 = эхний төлөлт, 2 = хоёр дахь төлөлт гэх мэт дараалал бөгөөд данс тус бүрд
    // дахин 1-ээс эхэлдэг тул ACCOUNTID-тай хослон мөрийг заана.
    @Id
    @Column(name = "ACCOUNTID")
    private String accountId;
    @Id
    @Column(name = "INSTALLMENTID")
    private Long installmentId;
    @Column(name = "BRANCHID")
    private String branchId;
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