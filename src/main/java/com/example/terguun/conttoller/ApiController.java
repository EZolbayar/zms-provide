package com.example.terguun.conttoller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terguun.dto.AccountDto;
import com.example.terguun.dto.sain.CitizenUploadRequestDto;
import com.example.terguun.service.AccountService;
import com.example.terguun.service.CitizenUploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final AccountService accountService;
    private final CitizenUploadService citizenUploadService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/citizen-upload/{clientId}")
    public ResponseEntity<CitizenUploadRequestDto> getCitizenUpload(@PathVariable Long clientId) {
        return ResponseEntity.ok(citizenUploadService.buildForClient(clientId));
    }

}
