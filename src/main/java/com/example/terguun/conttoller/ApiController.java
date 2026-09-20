package com.example.terguun.conttoller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.terguun.dto.AccountDto;
import com.example.terguun.dto.CustomerDto;
import com.example.terguun.dto.HttpResponse;
import com.example.terguun.dto.Login;
import com.example.terguun.dto.RecentCustomerData;
import com.example.terguun.dto.RecentUploadItem;
import com.example.terguun.dto.UploadRequest;
import com.example.terguun.dto.sain.CitizenUploadResponse;
import com.example.terguun.dto.sain.CustomerData;
import com.example.terguun.service.AccountService;
import com.example.terguun.service.CustomerService;
import com.example.terguun.service.LoginService;
import com.example.terguun.service.RecentlyDataService;
import com.example.terguun.service.SainUploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final LoginService loginService;
    private final AccountService accountService;
    private final RecentlyDataService recentlyDataService;
    private final SainUploadService sainUploadService;
    private final CustomerService customerService;

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

    /**
     * Нийлүүлэх мэдээллийн нэгдсэн жагсаалт: шинэ зээл, хаагдсан зээл, эргэн төлөлт гурвыг нэг
     * дуудалтаар буцаана. Мөр бүр дээрээ changeType талбартай.
     */
    @GetMapping("/send-data")
    public ResponseEntity<HttpResponse<List<RecentCustomerData>>> getRecentChanges() {
        return ResponseEntity.ok(HttpResponse.success(recentlyDataService.buildForRecentChanges()));
    }

    @PostMapping("/send-data")
    public ResponseEntity<HttpResponse<List<CustomerData>>> getCitizenUploadForClients(
            @RequestBody List<String> clientIds) {
        return ResponseEntity.ok(HttpResponse.success(recentlyDataService.buildForClients(clientIds)));
    }

    /**
     * Жагсаалтаас сонгосон мөрүүдийг ЗМС рүү илгээнэ. Payload-ыг эндээс л угсардаг тул жагсаалтын
     * хариу хөнгөн үлдэнэ.
     */
    @PostMapping("/send-data/upload")
    public ResponseEntity<HttpResponse<List<CitizenUploadResponse>>> uploadRecentChanges(
            @RequestBody List<RecentUploadItem> items,
            @RequestHeader(value = "X-User-Id", required = false) String userId) {
        return ResponseEntity.ok(HttpResponse.success(sainUploadService
                .upload(recentlyDataService.buildForUpload(items), userId)));
    }

    @GetMapping("/customers")
    public ResponseEntity<HttpResponse<List<CustomerDto>>> getCustomers() {
        return ResponseEntity.ok(HttpResponse.success(customerService.getAll()));
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<HttpResponse<CustomerDto>> getCustomer(@PathVariable String customerId) {
        return ResponseEntity.ok(HttpResponse.success(customerService.getById(customerId)));
    }

    /** Шинэ харилцагчийг TBCUSTOMERS-д бүртгэнэ. Бүртгэсэн хэрэглэгчийг X-User-Id header-ээр авна. */
    @PostMapping("/customers")
    public ResponseEntity<HttpResponse<CustomerDto>> createCustomer(
            @RequestBody CustomerDto request,
            @RequestHeader(value = "X-User-Id", required = false) String userId) {
        return ResponseEntity.ok(HttpResponse.success(customerService.create(request, userId)));
    }

    /** Харилцагчийн мэдээллийг засна. Засварласан хэрэглэгчийг X-User-Id header-ээр авна. */
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<HttpResponse<CustomerDto>> updateCustomer(
            @PathVariable String customerId,
            @RequestBody CustomerDto request,
            @RequestHeader(value = "X-User-Id", required = false) String userId) {
        return ResponseEntity.ok(HttpResponse.success(customerService.update(customerId, request, userId)));
    }

    @PostMapping("/upload-sain")
    public ResponseEntity<HttpResponse<List<CitizenUploadResponse>>> uploadCitizen(@RequestBody UploadRequest request) {

        List<CitizenUploadResponse> response = sainUploadService.uploadCitizenData(request.getData());

        return ResponseEntity.ok(HttpResponse.success(response));
    }

}
