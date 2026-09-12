package com.example.terguun.dto.sain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonProperty;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CollateralInformation {

    private String action;
 
    @JsonProperty("o_c_coll_index")
    private String index;
 
    @JsonProperty("o_c_coll_contractno")
    private String contractNo;
 
    @JsonProperty("o_c_coll_internalno")
    private String internalNo;
 
    @JsonProperty("o_c_coll_type")
    private String type;
 
    @JsonProperty("o_c_coll_description")
    private String description;
 
    @JsonProperty("o_c_coll_valuation_date")
    private LocalDate valuationDate;
 
    @JsonProperty("o_c_coll_value")
    private BigDecimal value;
 
    @JsonProperty("o_c_coll_max_value")
    private BigDecimal maxValue;
 
    @JsonProperty("o_c_coll_address")
    private String address;
 
    @JsonProperty("o_c_coll_zipcode")
    private String zipcode;
 
    @JsonProperty("o_c_coll_is_real_estate")
    private Integer isRealEstate;
 
    @JsonProperty("o_c_coll_state_registration")
    private CollateralStateRegistration stateRegistration;
 
    @JsonProperty("o_c_coll_other_registration")
    private CollateralOtherRegistration otherRegistration;
 
    @JsonProperty("o_c_coll_customer")
    private List<CollateralCustomer> customer;
 
    @JsonProperty("o_c_coll_org")
    private List<CollateralOrg> org;
}
