package com.example.terguun.dto.sain;

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
public class CitizenUploadRequestDto {

    @JsonProperty("patch_number")
    private String patchNumber;

    @JsonProperty("data_provider_regnum")
    private String dataProviderRegnum;

    @JsonProperty("data_provider_branch")
    private String dataProviderBranch;

    @JsonProperty("customer_data")
    private List<CustomerDataDto> customerData;
}
