package com.example.terguun.dto.sain;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonSerialize;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoanInformation {

private String action;
 
    @JsonProperty("o_c_loan_contract_date")
    private LocalDate contractDate;
 
    @JsonProperty("o_c_loan_contractno")
    @JsonSerialize(using = DashIfBlankSerializer.class, nullsUsing = DashIfNullSerializer.class)
    private String contractNo;
 
    @JsonProperty("o_c_loan_contract_change_reason")
    private String contractChangeReason;
 
    @JsonProperty("o_c_loan_collateral_indexes")
    private List<String> collateralIndexes;
 
    @JsonProperty("o_c_loan_related_org_indexes")
    private List<String> relatedOrgIndexes;
 
    @JsonProperty("o_c_loan_related_customer_indexes")
    private List<String> relatedCustomerIndexes;
 
    @JsonProperty("o_c_loan_amount_lcy")
    private BigDecimal amountLcy;
 
    @JsonProperty("o_c_loan_amount_fcy")
    private BigDecimal amountFcy;
 
    @JsonProperty("o_c_loan_balance_lcy")
    private BigDecimal balanceLcy;
 
    @JsonProperty("o_c_loan_balance_fcy")
    private BigDecimal balanceFcy;
 
    @JsonProperty("o_c_loan_interest_balance_lcy")
    private BigDecimal interestBalanceLcy;
 
    @JsonProperty("o_c_loan_interest_balance_fcy")
    private BigDecimal interestBalanceFcy;
 
    @JsonProperty("o_c_loan_additional_interest_balance_lcy")
    private BigDecimal additionalInterestBalanceLcy;
 
    @JsonProperty("o_c_loan_additional_interest_balance_fcy")
    private BigDecimal additionalInterestBalanceFcy;
 
    @JsonProperty("o_c_loan_currency_rate")
    @JsonSerialize(nullsUsing = DashIfNullSerializer.class)
    private BigDecimal currencyRate;
 
    @JsonProperty("o_c_loan_loan_provenance")
    private String loanProvenance;
 
    @JsonProperty("o_c_loan_bond_market")
    private String bondMarket;
 
    @JsonProperty("o_c_loan_numof_bonds")
    private Integer numOfBonds;
 
    @JsonProperty("o_c_loan_bond_unit_price")
    private BigDecimal bondUnitPrice;
 
    @JsonProperty("o_c_loan_starteddate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startedDate;
 
    @JsonProperty("o_c_loan_expdate")
    @JsonSerialize(nullsUsing = DashIfNullSerializer.class)
    private LocalDate expDate;
 
    @JsonProperty("o_c_loan_status")
    @JsonSerialize(using = DashIfBlankSerializer.class, nullsUsing = DashIfNullSerializer.class)
    private String status;
 
    @JsonProperty("o_c_loan_decide_status")
    private String decideStatus;
 
    @JsonProperty("o_c_loan_paiddate")
    private LocalDate paidDate;
 
    @JsonProperty("o_c_loan_currency")
    private String currency;
 
    @JsonProperty("o_c_loan_sector")
    // @JsonSerialize(using = DashIfBlankSerializer.class, nullsUsing = DashIfNullSerializer.class)
    private String sector;
 
    @JsonProperty("o_c_loan_interest_rate")
    private BigDecimal interestRate;
 
    @JsonProperty("o_c_loan_additional_interest_rate")
    private BigDecimal additionalInterestRate;
 
    @JsonProperty("o_c_loan_commission")
    private BigDecimal commission;
 
    @JsonProperty("o_c_loan_fee")
    private BigDecimal fee;
 
    @JsonProperty("o_c_loan_class")
    @JsonSerialize(using = DashIfBlankSerializer.class, nullsUsing = DashIfNullSerializer.class)
    private String loanClass;
 
    @JsonProperty("o_c_loan_type")
    // @JsonSerialize(using = DashIfBlankSerializer.class, nullsUsing = DashIfNullSerializer.class)
    private String type;
 
    @JsonProperty("o_c_loan_line_contractno")
    private String lineContractNo;
 
    @JsonProperty("o_c_loan_description")
    private String description;

    @JsonProperty("o_c_loan_transactions")
    private LoanTransactions loanTransactions;

}
