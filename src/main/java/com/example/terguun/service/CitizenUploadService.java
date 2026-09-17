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
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

@Log4j2
@Service
@RequiredArgsConstructor
public class CitizenUploadService {

    private final SainServiceClient sainServiceClient;

    private final ObjectMapper sainResponseMapper = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();

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
            String rawBody = null;
            try {
                log.debug("Citizen upload илгээж байна. patchNumber={}", patchNumber);
                rawBody = sainServiceClient.uploadCitizen(request);
                responses.add(parseResponse(rawBody));
            } catch (Exception ex) {
                failureCount++;
                log.error("Citizen upload амжилтгүй боллоо. patchNumber={}, body={}, error={}",
                        patchNumber, rawBody, ex.getMessage(), ex);
                // Always add one response per request so the caller can zip requests/responses by index.
                responses.add(CitizenUploadResponse.builder()
                        .success(false)
                        .errors(new String[] { ex.getMessage() })
                        .build());
            }
        }
 
        log.info("Citizen upload batch дууслаа. Нийт={}, амжилттай={}, амжилтгүй={}",
                requests.size(), responses.size(), failureCount);
 
        return responses;

    }

    /** Sain returns either a single object or a one-element array, so both shapes are accepted. */
    private CitizenUploadResponse parseResponse(String rawBody) {
        if (rawBody == null || rawBody.isBlank()) {
            return CitizenUploadResponse.builder().success(true).build();
        }
        JsonNode node = sainResponseMapper.readTree(rawBody);
        if (node.isArray()) {
            node = node.isEmpty() ? null : node.get(0);
        }
        if (node == null || node.isNull()) {
            return CitizenUploadResponse.builder().success(true).build();
        }
        return sainResponseMapper.treeToValue(node, CitizenUploadResponse.class);
    }

}