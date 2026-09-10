package com.example.terguun.service;

import com.example.terguun.dto.sain.CitizenUploadRequestDto;

public interface CitizenUploadService {

    CitizenUploadRequestDto buildForClient(Long clientId);
}
