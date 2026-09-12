package com.example.terguun.service;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.terguun.client.SainTokenClient;
import com.example.terguun.dto.sain.SainToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class SainTokenService {

    private final SainTokenClient sainClient;

    @Value("${sain.username}")
    private String username;

    @Value("${sain.password}")
    private String password;

    private final AtomicReference<String> cachedToken = new AtomicReference<>();

    public String getToken() {
        String token = cachedToken.get();
        if (token == null) {
            token = fetchToken();
            cachedToken.set(token);
        }
        return token;
    }

    private String fetchToken() {
        SainToken.Response response = sainClient.getToken(SainToken.Request.builder()
                .username(username)
                .password(password)
                .build());
        log.debug("Fetched new Sain token");
        return response.getToken();
    }

}
