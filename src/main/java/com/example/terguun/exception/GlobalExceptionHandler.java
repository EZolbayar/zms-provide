package com.example.terguun.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.terguun.dto.HttpResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<HttpResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.error("ResourceNotFoundException occured: ", exception);

        final int status = HttpStatus.NOT_FOUND.value();
        final String errorCode = exception.getErrorCode();
        final String errorMessage = exception.getMessage();

        final HttpResponse<Void> httpResponse = HttpResponse.<Void>builder()
                .status(status)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();

        log.info("Response: {}", httpResponse);
        return ResponseEntity.status(status).body(httpResponse);
    }

    @ExceptionHandler({ MissingRequestHeaderException.class })
    public ResponseEntity<HttpResponse<Void>> handleMissingRequestHeaderException(MissingRequestHeaderException exception) {
        log.error("MissingRequestHeaderException occured: ", exception);

        final int status = HttpStatus.BAD_REQUEST.value();
        final String errorCode = "ERR001";
        final String errorMessage = "Шаардлагатай header дутуу байна";

        final HttpResponse<Void> httpResponse = HttpResponse.<Void>builder()
                .status(status)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();

        log.info("Response: {}", httpResponse);
        return ResponseEntity.status(status).body(httpResponse);
    }

    @ExceptionHandler({ BadRequestException.class })
    public ResponseEntity<HttpResponse<Void>> handleBadRequestException(BadRequestException exception) {
        log.error("BadRequestException occured: ", exception);

        final int status = HttpStatus.BAD_REQUEST.value();
        final String errorCode = exception.getErrorCode();
        final String errorMessage = exception.getMessage();

        final HttpResponse<Void> httpResponse = HttpResponse.<Void>builder()
                .status(status)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();

        log.info("Response: {}", httpResponse);
        return ResponseEntity.status(status).body(httpResponse);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<HttpResponse<Void>> handleMethodArgumentNotValidExcpetion(
            MethodArgumentNotValidException exception) {
        log.error("MethodArgumentNotValidException occured: ", exception);

        int status = HttpStatus.BAD_REQUEST.value();
        String errorCode = "ERR003";
        String errorMessage = "Хүсэлт алдаатай байна";
        Map<String, String> errorFields = exception.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing));

        HttpResponse<Void> httpResponse = HttpResponse.<Void>builder()
                .status(status)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .errorFields(errorFields)
                .build();

        log.info("Response: {}", httpResponse);

        return ResponseEntity.status(status).body(httpResponse);
    }

    @ExceptionHandler({ InvalidRequestException.class })
    public ResponseEntity<HttpResponse<Void>> handleInvalidRequestException(InvalidRequestException exception) {
        log.error("InvalidRequestException occured: ", exception);

        int status = HttpStatus.BAD_REQUEST.value();
        String errorCode = "ERR003";
        String errorMessage = "Хүсэлт алдаатай байна";

        Map<String, String> errorFields = exception.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing));

        HttpResponse<Void> httpResponse = HttpResponse.<Void>builder()
                .status(status)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .errorFields(errorFields)
                .build();

        log.info("Response: {}", httpResponse);

        return ResponseEntity.status(status).body(httpResponse);
    }

    @ExceptionHandler({ SainException.class })
    public ResponseEntity<HttpResponse<Void>> handleSainException(SainException exception) {
        log.debug("SainException occured: {}", exception);

        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = exception.getErrorMessage();
        String code = exception.getErrorCode();

        HttpResponse<Void> response = HttpResponse.<Void>builder()
                .status(status)
                .errorMessage(message)
                .errorCode(code)
                .build();

        log.info("Response: {}", response);
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<HttpResponse<Void>> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error("IllegalArgumentException occured: ", exception);

        final int status = HttpStatus.BAD_REQUEST.value();
        final String errorCode = "ERR004";
        final String errorMessage = exception.getMessage();

        final HttpResponse<Void> httpResponse = HttpResponse.<Void>builder()
                .status(status)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();

        log.info("Response: {}", httpResponse);
        return ResponseEntity.status(status).body(httpResponse);
    }

    @ExceptionHandler({ IllegalStateException.class })
    public ResponseEntity<HttpResponse<Void>> handleIllegalStateException(IllegalStateException exception) {
        log.error("IllegalStateException occured: ", exception);

        final int status = HttpStatus.CONFLICT.value();
        final String errorCode = "ERR005";
        final String errorMessage = exception.getMessage();

        final HttpResponse<Void> httpResponse = HttpResponse.<Void>builder()
                .status(status)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();

        log.info("Response: {}", httpResponse);
        return ResponseEntity.status(status).body(httpResponse);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<HttpResponse<Void>> handleException(Exception exception) {
        log.error("Unhandled exception occured: ", exception);

        final int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        final String errorCode = "BB001";
        final String errorMessage = "Дотоод серверийн алдаа гарлаа";

        final HttpResponse<Void> httpResponse = HttpResponse.<Void>builder()
                .status(status)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();

        log.info("Response: {}", httpResponse);
        return ResponseEntity.status(status).body(httpResponse);
    }

}
