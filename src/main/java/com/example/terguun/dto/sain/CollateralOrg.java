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

public class CollateralOrg {

    private String action;
 
    @JsonProperty("o_c_coll_org_name")
    private String name;
 
    @JsonProperty("o_c_coll_org_isforeign")
    private Integer isForeign;
 
    @JsonProperty("o_c_coll_org_regnum")
    private String regnum;
 
    @JsonProperty("o_c_coll_org_state_regnum")
    private String stateRegnum;

}
