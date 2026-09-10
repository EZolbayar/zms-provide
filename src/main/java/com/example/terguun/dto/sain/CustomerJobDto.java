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
public class CustomerJobDto {

    @JsonProperty("c_job_position")
    private String position;

    @JsonProperty("c_job_name")
    private String name;

    @JsonProperty("c_job_address")
    private String address;

    @JsonProperty("c_job_phone")
    private String phone;

    @JsonProperty("c_job_mail")
    private String mail;
}
