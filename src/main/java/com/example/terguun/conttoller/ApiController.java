package com.example.terguun.conttoller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terguun.dto.AccountDto;
import com.example.terguun.dto.sain.CitizenUploadRequest;
import com.example.terguun.service.AccountService;
import com.example.terguun.service.RecentlyDataService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final AccountService accountService;
    private final RecentlyDataService recentlyDataService;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDto>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/send-data")
    public ResponseEntity<CitizenUploadRequest> getCitizenUpload() {
        return ResponseEntity.ok(recentlyDataService.buildForRecentlyChangedAccounts().stream().findFirst().orElse(null));
    }

}
