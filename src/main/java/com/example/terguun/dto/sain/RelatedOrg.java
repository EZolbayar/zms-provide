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
public class RelatedOrg {

    private String action;
 
    @JsonProperty("o_c_related_org_index")
    private String index;
 
    @JsonProperty("o_c_related_org_name")
    private String name;
 
    @JsonProperty("o_c_related_org_isforeign")
    private Integer isForeign;
 
    @JsonProperty("o_c_related_org_regnum")
    private String regnum;
 
    @JsonProperty("o_c_related_org_state_regnum")
    private String stateRegnum;
 
    @JsonProperty("o_c_related_org_relation")
    private String relation;
 
    @JsonProperty("o_c_related_org_isfinancial_onus")
    private Integer isFinancialOnus;

}
