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
public class RelatedCustomer {

    private String action;
 
    @JsonProperty("o_c_related_customer_index")
    private String index;
 
    @JsonProperty("o_c_related_customer_firstname")
    private String firstname;
 
    @JsonProperty("o_c_related_customer_lastname")
    private String lastname;
 
    @JsonProperty("o_c_related_customer_familyname")
    private String familyname;
 
    @JsonProperty("o_c_related_customer_isforeign")
    private Integer isForeign;
 
    @JsonProperty("o_c_related_customer_civil_id")
    private String civilId;
 
    @JsonProperty("o_c_related_customer_regnum")
    private String regnum;
 
    @JsonProperty("o_c_related_customer_relation")
    private String relation;
 
    @JsonProperty("o_c_related_customer_isfinancial_onus")
    private Integer isFinancialOnus;

}
