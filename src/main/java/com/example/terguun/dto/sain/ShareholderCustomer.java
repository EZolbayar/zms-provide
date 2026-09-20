package com.example.terguun.dto.sain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Хувьцаа эзэмшигч иргэн, 25%-иас дээш (протокол 6). */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShareholderCustomer {

    private String action;

    @JsonProperty("o_shareholder_customer_civil_id")
    private String civilId;

    @JsonProperty("o_shareholder_customer_regnum")
    private String regnum;

    @JsonProperty("o_shareholder_customer_firstname")
    private String firstname;

    @JsonProperty("o_shareholder_customer_lastname")
    private String lastname;

    @JsonProperty("o_shareholder_customer_familyname")
    private String familyname;

    @JsonProperty("o_shareholder_customer_isforeign")
    private Integer isForeign;

    @JsonProperty("o_shareholder_customer_address")
    private String address;

    @JsonProperty("o_shareholder_customer_phone")
    private String phone;

    @JsonProperty("o_shareholder_customer_email")
    private String email;
}
