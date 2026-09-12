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
public class CustomerData {

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
    private CustomerAddress address;

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
    private CustomerJob job;

    @JsonProperty("o_c_related_org")
    private RelatedOrg relatedOrg;

    @JsonProperty("o_c_related_customer")
    private RelatedCustomer relatedCustomer;

    @JsonProperty("o_c_customer_bank_relation")
    private CustomerBankRelation bankRelation;

    @JsonProperty("o_c_loan_information")
    private LoanInformation loanInformation;

    @JsonProperty("o_c_loanline")
    private LoanLineData loanLineData;

    @JsonProperty("o_c_coll_information")
    private CollateralInformation collateralInformation;
}
