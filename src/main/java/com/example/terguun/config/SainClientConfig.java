package com.example.terguun.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.terguun.client.SainClientErrorDecoder;
import com.example.terguun.service.SainTokenService;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
@Configuration
public class SainClientConfig {

    @Value("${sain.login}")
    private String loginPath;

     @Bean(name = "sainLoggerLevel")
    Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean(name = "sainErrorDecoder")
    SainClientErrorDecoder sainClientErrorDecoder() {
        return new SainClientErrorDecoder();
    }

    @Bean(name = "sainRequestOptions")
    Request.Options sainRequestOptions() {
        Duration connectTimeout = Duration.ofMinutes(4);
        Duration readTimeout = Duration.ofMinutes(4);
        return new Request.Options(connectTimeout, readTimeout, true);
    }

    @Bean(name = "sainAuthRequestInterceptor")
    RequestInterceptor sainAuthRequestInterceptor(SainTokenService sainTokenService) {
        return requestTemplate -> {
            // Login дуудлагад Bearer token нэмэхгүй, эс тэгвээс токен авах үед мөнхөд эргэлдэнэ.
            if (requestTemplate.url() != null && requestTemplate.url().contains(loginPath)) {
                return;
            }
            requestTemplate.header("Authorization", "Bearer " + sainTokenService.getToken());
        };
    }

}
