package com.example.terguun.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressDto {

    @JsonProperty("o_c_address_full")
    private String addressFull;

    @JsonProperty("o_c_address_aimag_city_name")
    private String aimagCityName;

    @JsonProperty("o_c_address_aimag_city_code")
    private String aimagCityCode;

    @JsonProperty("o_c_address_soum_district_name")
    private String soumDistrictName;

    @JsonProperty("o_c_address_soum_district_code")
    private String soumDistrictCode;

    @JsonProperty("o_c_address_bag_khoroo_name")
    private String bagKhorooName;

    @JsonProperty("o_c_address_bag_khoroo_code")
    private String bagKhorooCode;

    @JsonProperty("o_c_address_street_name")
    private String streetName;

    @JsonProperty("o_c_address_region_name")
    private String regionName;

    @JsonProperty("o_c_address_town_name")
    private String townName;

    @JsonProperty("o_c_address_apartment_name")
    private String apartmentName;

    @JsonProperty("o_c_address_zipcode")
    private Integer zipcode;
}
