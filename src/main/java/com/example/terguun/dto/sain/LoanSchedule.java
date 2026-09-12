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
public class LoanSchedule {

    private String action;
    @JsonProperty("o_c_schedule_due_date")
    private String dueDate;
    @JsonProperty("o_c_schedule_principal")
    private String principal;
    @JsonProperty("o_c_schedule_interest")
    private String interest;
    @JsonProperty("o_c_schedule_additional")
    private String additional;
    @JsonProperty("o_c_schedule_balance")
    private String balance;

}
