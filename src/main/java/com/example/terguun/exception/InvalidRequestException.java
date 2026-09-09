package com.example.terguun.exception;

import org.springframework.validation.BindingResult;

import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException {

    private final String errorCode = "INVALID_REQUEST";
    private final BindingResult bindingResult;

    public InvalidRequestException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

}
