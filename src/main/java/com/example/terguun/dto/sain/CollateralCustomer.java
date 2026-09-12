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

public class CollateralCustomer {

    private String action;
 
    @JsonProperty("o_c_coll_customer_firstname")
    private String firstname;
 
    @JsonProperty("o_c_coll_customer_lastname")
    private String lastname;
 
    @JsonProperty("o_c_coll_customer_familyname")
    private String familyname;
 
    @JsonProperty("o_c_coll_customer_isforeign")
    private Integer isForeign;
 
    @JsonProperty("o_c_coll_customer_civil_id")
    private String civilId;
 
    @JsonProperty("o_c_coll_customer_regnum")
    private String regnum;
}
