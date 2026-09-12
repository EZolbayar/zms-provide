package com.example.terguun.dto.sain;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoanPayment {

    private String action;
    @JsonProperty("o_c_payment_due_date")
    private String dueDate;
    @JsonProperty("o_c_payment_principal")
    private String principal;
    @JsonProperty("o_c_payment_interest")
    private String interest;
    @JsonProperty("o_c_payment_additional")
    private String additional;
    @JsonProperty("o_c_payment_balance")
    private String balance;

}
