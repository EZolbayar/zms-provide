package com.example.terguun.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.terguun.dto.sain.CustomerAddress;
import com.example.terguun.dto.sain.CustomerBankRelation;
import com.example.terguun.dto.sain.CustomerData;
import com.example.terguun.dto.sain.LoanInformation;
import com.example.terguun.dto.sain.LoanSchedule;
import com.example.terguun.dto.sain.LoanTransactions;
import com.example.terguun.exception.ResourceNotFoundException;
import com.example.terguun.model.Account;
import com.example.terguun.model.Client;
import com.example.terguun.model.LoanInstallment;
import com.example.terguun.repository.AccountRepository;
import com.example.terguun.repository.ClientRepository;
import com.example.terguun.repository.LoanInstallmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class RecentlyDataService {

        // "<аймаг/хот> аймаг|хот <сум/дүүрэг> сум|дүүрэг <баг>-р баг <гудамж> <байр>" хэлбэрийн хаягийг задлана.
        private static final Pattern ADDRESS_PATTERN = Pattern.compile(
                        "^(?<aimag>.+?(?:аймаг|хот))\\s+(?<soum>.+?(?:сум|дүүрэг))\\s+(?<bag>.+?(?:баг|хороо))\\s+(?<street>\\S+)\\s+(?<apartment>.+)$");

        @Value("${look.back.hours}")
        private int lookbackHours;

        private final ClientRepository clientRepository;
        private final AccountRepository accountRepository;
        private final LoanInstallmentRepository loanInstallmentRepository;

        public List<CustomerData> buildForRecentlyChangedAccounts() {
                LocalDateTime since = LocalDateTime.now().minusHours(lookbackHours);

                List<Account> recentAccounts = accountRepository.findByCreatedOnAfterOrModifiedOnAfter(since, since);

                log.info("Found recent accounts: {}", recentAccounts);

                List<String> clientIds = recentAccounts.stream()
                                .map(Account::getClientId)
                                .distinct()
                                .collect(Collectors.toList());

                return clientIds.stream()
                                .map(this::buildForClient)
                                .collect(Collectors.toList());
        }

        public List<CustomerData> buildForClients(List<String> clientIds) {
                return clientIds.stream()
                                .distinct()
                                .map(this::buildForClient)
                                .collect(Collectors.toList());
        }

        private CustomerData buildForClient(String clientId) {
                Client client = clientRepository.findById(clientId)
                                .orElseThrow(() -> new ResourceNotFoundException("Client олдсонгүй, id: " + clientId));

                List<Account> accounts = accountRepository.findByClientId(clientId);
                List<Account> activeAccounts = accounts.stream()
                                .filter(account -> "A".equals(account.getAccountStatus()))
                                .collect(Collectors.toList());
                List<String> activeAccountIds = activeAccounts.stream()
                                .map(Account::getAccountId)
                                .collect(Collectors.toList());
                log.info("Found accounts for clientId: {}: activeAccountIds: {}", clientId, activeAccountIds);

                Account loanAccount;
                List<LoanInstallment> activeInstallments;
                if (!activeAccountIds.isEmpty()) {
                        activeInstallments = loanInstallmentRepository.findByAccountIdIn(activeAccountIds);
                        loanAccount = activeAccounts.stream()
                                        .max(Comparator.comparing(Account::getCreatedOn,
                                                        Comparator.nullsFirst(Comparator.naturalOrder())))
                                        .orElse(null);
                        log.info("Found active installments for clientId: {}: {}", clientId, activeInstallments);
                } else {
                        Optional<Account> latestAccount = accounts.stream()
                                        .max(Comparator.comparing(Account::getCreatedOn,
                                                        Comparator.nullsFirst(Comparator.naturalOrder())));
                        activeInstallments = latestAccount
                                        .map(account -> loanInstallmentRepository
                                                        .findByAccountIdIn(List.of(account.getAccountId())))
                                        .orElse(List.of());
                        loanAccount = latestAccount.orElse(null);
                        log.info("Found active installments for clientId: {}: {}", clientId, activeInstallments);
                }

                LoanInformation loanInformation = loanAccount == null ? null
                                : LoanInformation.builder()
                                                .action("add")
                                                .contractDate(loanAccount.getOpenDate() == null ? null
                                                                : loanAccount.getOpenDate().toLocalDate())
                                                .contractNo(loanAccount.getContractId())
                                                .amountLcy(loanAccount.getAppliedAmount())
                                                .balanceLcy(loanAccount.getBalance())
                                                .interestBalanceLcy(loanAccount.getInterestBalance())
                                                .additionalInterestBalanceLcy(loanAccount.getPenaltyBalance())
                                                .interestRate(loanAccount.getInterestRate())
                                                .startedDate(loanAccount.getOpenDate())
                                                .expDate(loanAccount.getMatureDate() == null ? null
                                                                : loanAccount.getMatureDate().toLocalDate())
                                                .status(loanAccount.getAccountStatus())
                                                .type(loanAccount.getAccountType())
                                                .loanTransactions(LoanTransactions.builder()
                                                                .loanSchedule(activeInstallments.stream()
                                                                                .map(this::toLoanSchedule)
                                                                                .collect(Collectors.toList()))
                                                                .build())
                                                .build();

                CustomerData customerData = CustomerData.builder()
                                .action("add")
                                .civilId("888954521912")
                                .regnum(client.getPinId())
                                .customerName(client.getClientName())
                                .lastname(client.getFirstName())
                                .familyname(client.getFamilyName())
                                .isForeign(0)
                                .birthdate(client.getBirthDate())
                                .address(parseAddress(client.getAddress1()))
                                .phone(client.getPhone1())
                                .email(client.getEmail() == null || client.getEmail().isBlank()
                                                ? "nomail@gmail.com"
                                                : client.getEmail())
                                .taxNumber(client.getOrgPinId())
                                // TBCLIENTS-д гэр бүлийн гишүүдийн тоо талбар байхгүй тул одоогоор хоосон
                                .familyNumOfMembers(2)
                                .isEmployed(activeInstallments.isEmpty() ? 0 : 1)
                                .loanInformation(loanInformation == null ? null : List.of(loanInformation))
                                .bankRelation(CustomerBankRelation.builder()
                                                .action("add")
                                                .relation(accounts.isEmpty() ? null : "01")
                                                .build())
                                .build();

                return customerData;
        }

        private CustomerAddress parseAddress(String addressFull) {
                CustomerAddress.CustomerAddressBuilder address = CustomerAddress.builder().addressFull(addressFull);
                Matcher matcher = addressFull == null ? null : ADDRESS_PATTERN.matcher(addressFull.trim());
                if (matcher != null && matcher.matches()) {
                        address.aimagCityName(matcher.group("aimag"))
                                        .soumDistrictName(matcher.group("soum"))
                                        .bagKhorooName(matcher.group("bag"))
                                        .streetName(matcher.group("street"))
                                        .apartmentName(matcher.group("apartment"));
                }
                // TBCLIENTS-д aimag/soum/bag khoroo кодууд тусад нь баганагүй тул одоогоор хоосон
                return address.build();
        }

        private LoanSchedule toLoanSchedule(LoanInstallment loanInstallment) {
                return LoanSchedule.builder()
                                .action("add")
                                .dueDate(loanInstallment.getDueDate() == null ? null
                                                : loanInstallment.getDueDate().toLocalDate().toString())
                                .principal(loanInstallment.getPrincipal() == null ? null
                                                : loanInstallment.getPrincipal().toString())
                                .interest(loanInstallment.getInterest() == null ? null
                                                : loanInstallment.getInterest().toString())
                                .balance(loanInstallment.getAfterBalance() == null ? null
                                                : loanInstallment.getAfterBalance().toString())
                                .build();
        }
}
