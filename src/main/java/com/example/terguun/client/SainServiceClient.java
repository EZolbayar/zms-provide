package com.example.terguun.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.terguun.config.SainClientConfig;
import com.example.terguun.dto.sain.CitizenUploadRequest;
import com.example.terguun.dto.sain.CitizenUploadResponse;

@FeignClient(name = "sainServiceClient", url = "${sain.baseUrl}", configuration = SainClientConfig.class)
public interface SainServiceClient {

    @PostMapping (value ="${sain.upload.citizen}")
    CitizenUploadResponse uploadCitizen(@RequestBody CitizenUploadRequest request);

}
