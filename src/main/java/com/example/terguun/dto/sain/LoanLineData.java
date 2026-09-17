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
 
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoanLineData {
     private String action;
 
    @JsonProperty("o_c_loanline_contract_date")
    private LocalDate contractDate;
 
    @JsonProperty("o_c_loanline_contractno")
    private String contractNo;
 
    @JsonProperty("o_c_loanline_contract_change_reason")
    private String contractChangeReason;
 
    @JsonProperty("o_c_loanline_collateral_indexes")
    private List<String> collateralIndexes;
 
    @JsonProperty("o_c_loanline_type")
    private String type;
 
    @JsonProperty("o_c_loanline_amount_lcy")
    private BigDecimal amountLcy;
 
    @JsonProperty("o_c_loanline_amount_fcy")
    private BigDecimal amountFcy;
 
    @JsonProperty("o_c_loanline_starteddate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startedDate;
 
    @JsonProperty("o_c_loanline_expdate")
    private LocalDate expDate;
 
    @JsonProperty("o_c_loanline_currency")
    private String currency;
 
    @JsonProperty("o_c_loanline_currency_rate")
    private BigDecimal currencyRate;
 
    @JsonProperty("o_c_loanline_sector")
    private String sector;
 
    @JsonProperty("o_c_loanline_interest_rate")
    private BigDecimal interestRate;
 
    @JsonProperty("o_c_loanline_commitment_interest_rate")
    private BigDecimal commitmentInterestRate;
 
    @JsonProperty("o_c_loanline_description")
    private String description;
 
    @JsonProperty("o_c_loanline_balance_lcy")
    private BigDecimal balanceLcy;
 
    @JsonProperty("o_c_loanline_balance_fcy")
    private BigDecimal balanceFcy;
 
    @JsonProperty("o_c_loanline_paiddate")
    private LocalDate paidDate;
 
    @JsonProperty("o_c_loanline_status")
    private String status;

}
