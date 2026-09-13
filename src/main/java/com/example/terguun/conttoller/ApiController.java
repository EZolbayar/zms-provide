package com.example.terguun.conttoller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terguun.dto.AccountDto;
import com.example.terguun.dto.HttpResponse;
import com.example.terguun.dto.Login;
import com.example.terguun.dto.sain.CitizenUploadResponse;
import com.example.terguun.dto.sain.CustomerData;
import com.example.terguun.service.AccountService;
import com.example.terguun.service.CitizenUploadService;
import com.example.terguun.service.LoginService;
import com.example.terguun.service.RecentlyDataService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final LoginService loginService;
    private final AccountService accountService;
    private final RecentlyDataService recentlyDataService;
    private final CitizenUploadService citizenUploadService;

    @PostMapping("/login")
    public ResponseEntity<HttpResponse<Login.Response>> login(@RequestBody Login.Request request) {
        Login.Response response = loginService.login(request);
        return ResponseEntity.ok(HttpResponse.success(response));
    }
 
    @PostMapping("/logout/{userId}")
    public ResponseEntity<HttpResponse<Void>> logout(@PathVariable String userId) {
        loginService.logout(userId);
        return ResponseEntity.ok(HttpResponse.success(null));
    }


    @GetMapping("/accounts")
    public ResponseEntity<HttpResponse<List<AccountDto>>> getAll() {
        return ResponseEntity.ok(HttpResponse.success(accountService.getAll()));
    }

    @GetMapping("/send-data")
    public ResponseEntity<HttpResponse<CustomerData>> getCitizenUpload() {
        CustomerData data = recentlyDataService.buildForRecentlyChangedAccounts().stream().findFirst().orElse(null);
        return ResponseEntity.ok(HttpResponse.success(data));
    }

    @PostMapping("/upload-citizen")
    public ResponseEntity<HttpResponse<List<CitizenUploadResponse>>> uploadCitizen(@RequestBody CustomerData entity) {
    
        List<CitizenUploadResponse> response = citizenUploadService.uploadCitizenData(List.of(entity));
        
        return ResponseEntity.ok(HttpResponse.success(response));
    }
    

}

