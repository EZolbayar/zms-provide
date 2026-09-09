package com.example.terguun.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final String errorCode = "BAD_REQUEST";

    public BadRequestException(String message) {
        super(message);
    }
}