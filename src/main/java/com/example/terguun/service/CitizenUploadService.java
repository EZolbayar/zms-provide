package com.example.terguun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.terguun.client.SainServiceClient;
import com.example.terguun.dto.sain.CitizenUploadRequest;
import com.example.terguun.dto.sain.CitizenUploadResponse;
import com.example.terguun.dto.sain.CustomerData;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CitizenUploadService {

    private final SainServiceClient sainServiceClient;

    @Value("${data.provider.regnum}")
    private String dataProviderRegnum;

    @Value("${data.provider.branch}")
    private String dataProviderBranch;

    public List<CitizenUploadResponse> uploadCitizenData(List<CustomerData> requests) {

        log.info("Citizen upload batch эхэллээ. Илгээх request тоо: {}", requests.size());
 
        List<CitizenUploadRequest> citizenUploadRequests = new ArrayList<>();
        List<CitizenUploadResponse> responses = new ArrayList<>();
        int failureCount = 0;
        requests.forEach(customerData -> {
            CitizenUploadRequest request = CitizenUploadRequest.builder()
                    .patchNumber(String.valueOf(System.currentTimeMillis()))
                    .dataProviderRegnum(dataProviderRegnum)
                    .dataProviderBranch(dataProviderBranch)
                    .customerData(List.of(customerData))
                    .build();
            citizenUploadRequests.add(request);
        });
 
        for (CitizenUploadRequest request : citizenUploadRequests) {
            String patchNumber = request.getPatchNumber();
            try {
                log.debug("Citizen upload илгээж байна. patchNumber={}", patchNumber);
                CitizenUploadResponse response = sainServiceClient.uploadCitizen(request);
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