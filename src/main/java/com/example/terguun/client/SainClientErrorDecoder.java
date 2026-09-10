package com.example.terguun.client;
import org.springframework.http.HttpStatus;

import com.example.terguun.exception.SainException;

import feign.Response;
import feign.Util;

import feign.codec.ErrorDecoder;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SainClientErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus == HttpStatus.UNAUTHORIZED) {
            return defaultDecoder.decode(methodKey, response);
        }
 
        if (responseStatus == HttpStatus.BAD_REQUEST) {
            return handleBadRequest(response);
        }

        return new SainException("Sain service error", requestUrl);

    }

        private Exception handleBadRequest(Response response) {
        String body = extractBody(response);
        String requestUrl = response.request().url();
        log.debug("Bad request to MSH: {}", body);

        if (body == null) {
            log.debug("url is {}",requestUrl);
            return new SainException("сайн сервис алдаа гарлаа body", requestUrl);
        }

        return new SainException("сайн сервис алдаа гарлаа bodygv", requestUrl);
    }

    private String extractBody(Response response) {
        try {
            if (response.body() == null)
                return null;
            return Util.toString(response.body().asReader(Util.UTF_8));
        } catch (Exception e) {
            return null;
        }
    }
}
