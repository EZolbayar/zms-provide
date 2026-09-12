package com.example.terguun.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.terguun.dto.sain.CitizenUploadRequest;
import com.example.terguun.dto.sain.CustomerAddress;
import com.example.terguun.dto.sain.CustomerBankRelation;
import com.example.terguun.dto.sain.CustomerData;
import com.example.terguun.exception.ResourceNotFoundException;
import com.example.terguun.model.Account;
import com.example.terguun.model.Client;
import com.example.terguun.model.LoanInstallment;
import com.example.terguun.repository.AccountRepository;
import com.example.terguun.repository.ClientRepository;
import com.example.terguun.repository.LoanInstallmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecentlyDataService {

    @Value("${look.back.hours}")
    private int lookbackHours;

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final LoanInstallmentRepository loanInstallmentRepository;

    @Value("${data.provider.regnum}")
    private String dataProviderRegnum;

    @Value("${data.provider.branch}")
    private String dataProviderBranch;

    public List<CitizenUploadRequest> buildForRecentlyChangedAccounts() {
        LocalDateTime since = LocalDateTime.now().minusHours(lookbackHours);

        List<Account> recentAccounts = accountRepository.findByCreatedOnAfterOrModifiedOnAfter(since, since);

        List<Long> clientIds = recentAccounts.stream()
                .map(Account::getClientId)
                .distinct()
                .collect(Collectors.toList());

        return clientIds.stream()
                .map(this::buildForClient)
                .collect(Collectors.toList());
    }

    private CitizenUploadRequest buildForClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client олдсонгүй, id: " + clientId));

        List<Account> accounts = accountRepository.findByClientId(clientId);
        List<Long> accountIds = accounts.stream().map(Account::getAccountId).collect(Collectors.toList());
        List<LoanInstallment> activeInstallments = accountIds.isEmpty()
                ? List.of()
                : loanInstallmentRepository.findByAccountIdInAndIsActive(accountIds, true);

        CustomerData customerData = CustomerData.builder()
                .action("add")
                .civilId(client.getNationalId())
                .regnum(client.getPinId())
                .customerName(client.getClientName())
                .lastname(client.getFirstName())
                .familyname(client.getFamilyName())
                .isForeign(0)
                .birthdate(client.getBirthDate())
                .address(CustomerAddress.builder()
                        .addressFull(client.getAddress1())
                        .apartmentName(client.getAddress2())
                        .build())
                .phone(client.getPhone1())
                .email(client.getEmail())
                .taxNumber(client.getOrgPinId())
                .isEmployed(activeInstallments.isEmpty() ? 0 : 1)
                .bankRelation(CustomerBankRelation.builder()
                        .action("add")
                        .relation(accounts.isEmpty() ? null : "01")
                        .build())
                .build();

        return CitizenUploadRequest.builder()
                .patchNumber(String.valueOf(System.currentTimeMillis()))
                .dataProviderRegnum(dataProviderRegnum)
                .dataProviderBranch(dataProviderBranch)
                .customerData(List.of(customerData))
                .build();
    }
}
