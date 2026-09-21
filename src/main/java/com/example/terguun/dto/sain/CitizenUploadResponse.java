package com.example.terguun.dto.sain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.JsonDeserialize;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CitizenUploadResponse {

    // Wrapper type: Sain occasionally returns "success": null, which fails to bind into a primitive boolean.
    @JsonProperty("success")
    private Boolean success;

    // Sain "errors"-ыг массив, объект эсвэл ганц текстээр буцаадаг.
    @JsonProperty("errors")
    @JsonDeserialize(using = FlexibleStringArrayDeserializer.class)
    private String[] errors;

    @JsonProperty("action")
    private String action;

    // RTE1017 үед Sain аль талбар шаардлага хангаагүйг "validate" объектоор буцаадаг: {"<талбарын зам>": "<алдааны код>"}.
    @JsonProperty("validate")
    @JsonDeserialize(using = FlexibleStringArrayDeserializer.class)
    private String[] validate;

}
