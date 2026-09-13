package com.example.terguun.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpResponse<T> {

    private int status;
    private T data;
    private String errorCode;
    private String errorMessage;
    private Map<String, String> errorFields;

    public static <T> HttpResponse<T> success(T data) {
        return HttpResponse.<T>builder()
                .status(200)
                .data(data)
                .build();
    }
}
