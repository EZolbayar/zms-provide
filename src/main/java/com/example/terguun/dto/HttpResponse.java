package com.example.terguun.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponse {

    private int status;
    private String errorCode;
    private String errorMessage;
    private Map<String, String> errorFields;
}
