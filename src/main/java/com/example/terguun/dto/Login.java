package com.example.terguun.dto;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Login {

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @JsonProperty("userId")
        private String userId;
        @JsonProperty("password")
        private String password;
    }

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        @JsonProperty("userId")
        private String userId;
        @JsonProperty("userName")
        private String userName;
        @JsonProperty("timeLoggedIn")
        private LocalDateTime timeLoggedIn;
        @JsonProperty("isAdmin")
        private Boolean isAdmin;

    }

}
