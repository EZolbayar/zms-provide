package com.example.terguun.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.terguun.config.SainClientConfig;

@FeignClient(name = "sainServiceClient", url = "${sain.baseUrl}", configuration = SainClientConfig.class)
public interface SainServiceClient {

    // @PostMapping (value ="${sain.upload.citizen}")
    //  uploadCitizen(@RequestBody CitizenRequest request);

}
