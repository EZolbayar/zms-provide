package com.example.terguun.dto.sain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Гүйцэтгэх удирдлагын мэдээлэл (протокол 4). */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrgCeo {

    @JsonProperty("o_ceo_civil_id")
    private String civilId;

    @JsonProperty("o_ceo_regnum")
    private String regnum;

    @JsonProperty("o_ceo_firstname")
    private String firstname;

    @JsonProperty("o_ceo_lastname")
    private String lastname;

    @JsonProperty("o_ceo_familyname")
    private String familyname;

    @JsonProperty("o_ceo_isforeign")
    private Integer isForeign;

    @JsonProperty("o_ceo_address")
    private String address;

    @JsonProperty("o_ceo_phone")
    private String phone;

    @JsonProperty("o_ceo_email")
    private String email;
}
