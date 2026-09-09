package com.example.terguun.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SainException extends RuntimeException {
    private String errorMessage;
    private String errorCode;

    public SainException(String errorMessage, String errorCode) {
        super("Sain service error");
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
