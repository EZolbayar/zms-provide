package com.example.terguun.client;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;

import com.example.terguun.config.SainClientConfig;
import com.example.terguun.dto.sain.SainToken;

@FeignClient(name = "sainTokenClient", url = "${sain.baseUrl}", configuration = SainClientConfig.class)
public interface SainTokenClient {

    @PostMapping(value = "${sain.login}")
    SainToken.Response getToken(@RequestBody SainToken.Request request);

}
