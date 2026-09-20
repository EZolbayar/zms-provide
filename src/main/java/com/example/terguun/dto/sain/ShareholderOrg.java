package com.example.terguun.dto.sain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Хувьцаа эзэмшигч байгууллага, 25%-иас дээш (протокол 5). */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShareholderOrg {

    private String action;

    @JsonProperty("o_shareholder_org_name")
    private String name;

    @JsonProperty("o_shareholder_org_isforeign")
    private Integer isForeign;

    @JsonProperty("o_shareholder_org_state_regnum")
    private String stateRegnum;

    @JsonProperty("o_shareholder_org_regnum")
    private String regnum;

    @JsonProperty("o_shareholder_org_address")
    private String address;

    @JsonProperty("o_shareholder_org_phone")
    private String phone;

    @JsonProperty("o_shareholder_org_email")
    private String email;
}
