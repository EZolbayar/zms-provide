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
public class citizenUploadResponse {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("errors")
    private String[] errors;

    @JsonProperty("action")
    private String action;

}
