package com.example.terguun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.terguun.client.SainServiceClient;
import com.example.terguun.dto.sain.CitizenUploadRequest;
import com.example.terguun.dto.sain.citizenUploadResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CitizenUploadService {

    @Value("${look.back.hours}")
    private int lookbackHours;

    private final SainServiceClient sainServiceClient;

    @Value("${data.provider.regnum}")
    private String dataProviderRegnum;

    @Value("${data.provider.branch}")
    private String dataProviderBranch;

    public List<citizenUploadResponse> uploadRecentlyData(List<CitizenUploadRequest> requests) {

        log.info("Citizen upload batch эхэллээ. Илгээх request тоо: {}", requests.size());
 
        List<citizenUploadResponse> responses = new ArrayList<>();
        int failureCount = 0;
 
        for (CitizenUploadRequest request : requests) {
            String patchNumber = request.getPatchNumber();
            try {
                log.debug("Citizen upload илгээж байна. patchNumber={}", patchNumber);
                citizenUploadResponse response = sainServiceClient.uploadCitizen(request);
                log.info("Citizen upload амжилттай. patchNumber={}", patchNumber);
                responses.add(response);
            } catch (Exception ex) {
                failureCount++;
                log.error("Citizen upload амжилтгүй боллоо. patchNumber={}, error={}",
                        patchNumber, ex.getMessage(), ex);
            }
        }
 
        log.info("Citizen upload batch дууслаа. Нийт={}, амжилттай={}, амжилтгүй={}",
                requests.size(), responses.size(), failureCount);
 
        return responses;

    }

}