package com.example.terguun.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.terguun.dto.sain.CitizenUploadRequestDto;
import com.example.terguun.dto.sain.CustomerAddressDto;
import com.example.terguun.dto.sain.CustomerBankRelationDto;
import com.example.terguun.dto.sain.CustomerDataDto;
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
public class CitizenUploadServiceImpl implements CitizenUploadService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final LoanInstallmentRepository loanInstallmentRepository;

    @Value("${sain.data-provider-regnum}")
    private String dataProviderRegnum;

    @Value("${sain.data-provider-branch}")
    private String dataProviderBranch;

    @Override
    public CitizenUploadRequestDto buildForClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client олдсонгүй, id: " + clientId));

        List<Account> accounts = accountRepository.findByClientId(clientId);
        List<Long> accountIds = accounts.stream().map(Account::getAccountId).collect(Collectors.toList());
        // Client-ийн идэвхтэй зээлийн эгзэмпляр байгаа эсэхээр ажил эрхлэлтийг тодорхойлно (өөр эх сурвалж байхгүй тул).
        List<LoanInstallment> activeInstallments = accountIds.isEmpty()
                ? List.of()
                : loanInstallmentRepository.findByAccountIdInAndIsActive(accountIds, true);

        CustomerDataDto customerData = CustomerDataDto.builder()
                .action("add")
                .civilId(client.getNationalId())
                .regnum(client.getPinId())
                .customerName(client.getClientName())
                .lastname(client.getFirstName())
                .familyname(client.getFamilyName())
                .isForeign(0)
                .birthdate(client.getBirthDate())
                .address(CustomerAddressDto.builder()
                        .addressFull(client.getAddress1())
                        .apartmentName(client.getAddress2())
                        .build())
                .phone(client.getPhone1())
                .email(client.getEmail())
                .taxNumber(client.getOrgPinId())
                .isEmployed(activeInstallments.isEmpty() ? 0 : 1)
                .bankRelation(CustomerBankRelationDto.builder()
                        .action("add")
                        .relation(accounts.isEmpty() ? null : "01")
                        .build())
                .build();

        return CitizenUploadRequestDto.builder()
                .patchNumber(String.valueOf(System.currentTimeMillis()))
                .dataProviderRegnum(dataProviderRegnum)
                .dataProviderBranch(dataProviderBranch)
                .customerData(List.of(customerData))
                .build();
    }
}
