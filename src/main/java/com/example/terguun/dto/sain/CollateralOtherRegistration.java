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

public class CollateralOtherRegistration {

    @JsonProperty("o_c_coll_other_certificateno")
    private String certificateNo;
 
    @JsonProperty("o_c_coll_other_regnum")
    private String regnum;
 
    @JsonProperty("o_c_coll_other_name")
    private String name;
 
    @JsonProperty("o_c_coll_other_registered_date")
    private LocalDate registeredDate;

}
