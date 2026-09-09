package com.example.terguun.service;

import com.example.terguun.dto.CitizenUploadRequestDto;

public interface CitizenUploadService {

    CitizenUploadRequestDto buildForClient(Long clientId);
}
