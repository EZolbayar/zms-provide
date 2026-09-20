package com.example.terguun.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.terguun.config.SainClientConfig;
import com.example.terguun.dto.sain.CitizenUploadRequest;
import com.example.terguun.dto.sain.EntityUploadRequest;

@FeignClient(name = "sainServiceClient", url = "${sain.baseUrl}", configuration = SainClientConfig.class)
public interface SainServiceClient {

    // Raw body: Sain returns either a single object or an array, so the service parses it defensively.
    @PostMapping(value = "${sain.upload.citizen}")
    String uploadCitizen(@RequestBody CitizenUploadRequest request);

    /** Хуулийн этгээдийн мэдээлэл. Хариу нь иргэнийхтэй ижил бүтэцтэй тул мөн raw body-гоор авна. */
    @PostMapping(value = "${sain.upload.entity}")
    String uploadEntity(@RequestBody EntityUploadRequest request);

}
