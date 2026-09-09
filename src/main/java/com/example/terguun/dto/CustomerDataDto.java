package com.example.terguun.dto;

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
public class CustomerDataDto {

    private String action;

    @JsonProperty("c_civil_id")
    private String civilId;

    @JsonProperty("o_c_regnum")
    private String regnum;

    @JsonProperty("o_c_customer_name")
    private String customerName;

    @JsonProperty("c_lastname")
    private String lastname;

    @JsonProperty("c_familyname")
    private String familyname;

    @JsonProperty("o_c_isforeign")
    private Integer isForeign;

    @JsonProperty("o_c_birthdate")
    private LocalDate birthdate;

    @JsonProperty("o_c_address")
    private CustomerAddressDto address;

    @JsonProperty("o_c_phone")
    private String phone;

    @JsonProperty("o_c_email")
    private String email;

    @JsonProperty("c_tax_number")
    private String taxNumber;

    @JsonProperty("c_family_numof_members")
    private Integer familyNumOfMembers;

    @JsonProperty("c_family_numof_unemployed")
    private Integer familyNumOfUnemployed;

    @JsonProperty("c_isemployed")
    private Integer isEmployed;

    @JsonProperty("c_job")
    private CustomerJobDto job;

    @JsonProperty("o_c_customer_bank_relation")
    private CustomerBankRelationDto bankRelation;
}
