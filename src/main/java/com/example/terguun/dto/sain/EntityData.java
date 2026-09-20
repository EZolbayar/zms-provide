package com.example.terguun.dto.sain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Хуулийн этгээдийн зээлдэгчийн мэдээлэл (протокол 2.4, entity_example.json). Зээл, зээлийн шугам,
 * барьцаа, холбогдох этгээдийн хэсгүүд нь иргэний бүтэцтэй яг ижил тул тэдгээрийн DTO-г дахин ашиглав.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EntityData {

    private String action;

    /** Байгууллагын улсын бүртгэлийн дугаар (протокол 1.2, заавал). */
    @JsonProperty("o_state_regnum")
    private String stateRegnum;

    /** Байгууллагын регистрийн дугаар, Монгол этгээдийн хувьд 7 оронтой тоо (протокол 1.3). */
    @JsonProperty("o_c_regnum")
    private String regnum;

    @JsonProperty("o_c_customer_name")
    private String customerName;

    @JsonProperty("o_c_isforeign")
    private Integer isForeign;

    /** Байгуулагдсан огноо. */
    @JsonProperty("o_c_birthdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @JsonProperty("o_c_address")
    private EntityAddress address;

    @JsonProperty("o_c_phone")
    private String phone;

    @JsonProperty("o_c_email")
    private String email;

    /** Нийт ажиллагсдын тоо, Annex 16. */
    @JsonProperty("o_numof_employee")
    private String numOfEmployee;

    /** Хариуцлагын хэлбэр, Annex 1. */
    @JsonProperty("o_company_type")
    private String companyType;

    @JsonProperty("o_orgrate")
    private OrgRate orgRate;

    @JsonProperty("o_ceo")
    private OrgCeo ceo;

    @JsonProperty("o_numof_shareholder_org")
    private Integer numOfShareholderOrg;

    @JsonProperty("o_shareholder_org")
    private List<ShareholderOrg> shareholderOrg;

    @JsonProperty("o_numof_shareholder_customer")
    private Integer numOfShareholderCustomer;

    @JsonProperty("o_shareholder_customer")
    private List<ShareholderCustomer> shareholderCustomer;

    @JsonProperty("o_c_related_org")
    private List<RelatedOrg> relatedOrg;

    @JsonProperty("o_c_related_customer")
    private List<RelatedCustomer> relatedCustomer;

    @JsonProperty("o_c_customer_bank_relation")
    private CustomerBankRelation bankRelation;

    @JsonProperty("o_c_loan_information")
    private List<LoanInformation> loanInformation;

    @JsonProperty("o_c_loanline")
    private List<LoanLineData> loanLineData;

    @JsonProperty("o_c_coll_information")
    private List<CollateralInformation> collateralInformation;
}
