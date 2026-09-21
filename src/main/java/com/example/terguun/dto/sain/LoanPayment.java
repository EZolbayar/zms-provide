package com.example.terguun.dto.sain;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.databind.annotation.JsonSerialize;

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
    // Протокол 10.2: төлөлт хийгдсэн огноо, YYYY-MM-DD HH:mm:ss.SSSSSS (заавал, UPDATE хийх боломжгүй).
    @JsonProperty("o_c_payment_date")
    private String paymentDate;

    @JsonProperty("o_c_payment_due_date")
    private String dueDate;
    @JsonProperty("o_c_payment_principal")
    private String principal;
    @JsonProperty("o_c_payment_interest")
    @JsonSerialize(using = DashIfBlankSerializer.class, nullsUsing = DashIfNullSerializer.class)
    private String interest;
    @JsonProperty("o_c_payment_additional")
    private String additional;
}
