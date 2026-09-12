package com.example.terguun.dto.sain;

import java.time.LocalDate;
 
import com.fasterxml.jackson.annotation.JsonProperty;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CollateralStateRegistration {

    @JsonProperty("o_c_coll_certificateno")
    private String certificateNo;
 
    @JsonProperty("o_c_coll_state_regnum")
    private String stateRegnum;
 
    @JsonProperty("o_c_coll_registered_date")
    private LocalDate registeredDate;
 
    @JsonProperty("o_c_coll_confirmed_date")
    private LocalDate confirmedDate;
}
