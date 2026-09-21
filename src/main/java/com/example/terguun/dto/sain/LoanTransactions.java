package com.example.terguun.dto.sain;
import java.util.List;

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
public class LoanTransactions {

    @JsonProperty("o_c_loan_schedule_type")
    @JsonSerialize(using = DashIfBlankSerializer.class, nullsUsing = DashIfNullSerializer.class)
    private String loanScheduleType;
    @JsonProperty("o_c_loan_schedule_status")
    private String loanScheduleStatus;
    @JsonProperty("o_c_loan_schedule_change_reason")
    private String loanScheduleChangeReason;
    @JsonProperty("o_c_loan_schedule")
    private List<LoanSchedule> loanSchedule;
    @JsonProperty("o_c_loan_payment")
    private List<LoanPayment> loanPayment;

}
