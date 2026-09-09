package com.example.terguun.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.example.terguun.dto.AccountDto;
import com.example.terguun.exception.ResourceNotFoundException;
import com.example.terguun.model.Account;
import com.example.terguun.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id) {
        return toDto(findEntity(id));
    }

    @Override
    public AccountDto create(AccountDto dto) {
        Account account = toEntity(dto);
        account.setAccountId(null);
        return toDto(accountRepository.save(account));
    }

    @Override
    public AccountDto update(Long id, AccountDto dto) {
        Account account = findEntity(id);
        Account updated = toEntity(dto).toBuilder().accountId(account.getAccountId()).build();
        return toDto(accountRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        accountRepository.delete(findEntity(id));
    }

    private Account findEntity(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account олдсонгүй, id: " + id));
    }

    private AccountDto toDto(Account account) {
        return AccountDto.builder()
                .accountId(account.getAccountId())
                .branchId(account.getBranchId())
                .productId(account.getProductId())
                .clientId(account.getClientId())
                .accountType(account.getAccountType())
                .accountStatus(account.getAccountStatus())
                .statusDate(account.getStatusDate())
                .openDate(account.getOpenDate())
                .balance(account.getBalance())
                .frozenAmount(account.getFrozenAmount())
                .interestRate(account.getInterestRate())
                .excessRate(account.getExcessRate())
                .term(account.getTerm())
                .matureDate(account.getMatureDate())
                .contractId(account.getContractId())
                .numOwners(account.getNumOwners())
                .glPointerId(account.getGlPointerId())
                .installmentTypeId(account.getInstallmentTypeId())
                .appliedAmount(account.getAppliedAmount())
                .sanctionDate(account.getSanctionDate())
                .sanctionInfo(account.getSanctionInfo())
                .grantedAmount(account.getGrantedAmount())
                .grantedBy(account.getGrantedBy())
                .disbursedAmount(account.getDisbursedAmount())
                .disbursedDate(account.getDisbursedDate())
                .cumulativePenPaid(account.getCumulativePenPaid())
                .cumulativeIntPaid(account.getCumulativeIntPaid())
                .cumulativePriPaid(account.getCumulativePriPaid())
                .overduePrincipal(account.getOverduePrincipal())
                .overdueDays(account.getOverdueDays())
                .accruedPenalty(account.getAccruedPenalty())
                .accruedInterest(account.getAccruedInterest())
                .intEffDate(account.getIntEffDate())
                .interestBalance(account.getInterestBalance())
                .penaltyBalance(account.getPenaltyBalance())
                .lastTrxDate(account.getLastTrxDate())
                .lastSerialId(account.getLastSerialId())
                .closeReasonId(account.getCloseReasonId())
                .isLocked(account.getIsLocked())
                .lockedBy(account.getLockedBy())
                .createdOn(account.getCreatedOn())
                .createdBy(account.getCreatedBy())
                .modifiedOn(account.getModifiedOn())
                .modifiedBy(account.getModifiedBy())
                .build();
    }

    private Account toEntity(AccountDto dto) {
        return Account.builder()
                .accountId(dto.getAccountId())
                .branchId(dto.getBranchId())
                .productId(dto.getProductId())
                .clientId(dto.getClientId())
                .accountType(dto.getAccountType())
                .accountStatus(dto.getAccountStatus())
                .statusDate(dto.getStatusDate())
                .openDate(dto.getOpenDate())
                .balance(dto.getBalance())
                .frozenAmount(dto.getFrozenAmount())
                .interestRate(dto.getInterestRate())
                .excessRate(dto.getExcessRate())
                .term(dto.getTerm())
                .matureDate(dto.getMatureDate())
                .contractId(dto.getContractId())
                .numOwners(dto.getNumOwners())
                .glPointerId(dto.getGlPointerId())
                .installmentTypeId(dto.getInstallmentTypeId())
                .appliedAmount(dto.getAppliedAmount())
                .sanctionDate(dto.getSanctionDate())
                .sanctionInfo(dto.getSanctionInfo())
                .grantedAmount(dto.getGrantedAmount())
                .grantedBy(dto.getGrantedBy())
                .disbursedAmount(dto.getDisbursedAmount())
                .disbursedDate(dto.getDisbursedDate())
                .cumulativePenPaid(dto.getCumulativePenPaid())
                .cumulativeIntPaid(dto.getCumulativeIntPaid())
                .cumulativePriPaid(dto.getCumulativePriPaid())
                .overduePrincipal(dto.getOverduePrincipal())
                .overdueDays(dto.getOverdueDays())
                .accruedPenalty(dto.getAccruedPenalty())
                .accruedInterest(dto.getAccruedInterest())
                .intEffDate(dto.getIntEffDate())
                .interestBalance(dto.getInterestBalance())
                .penaltyBalance(dto.getPenaltyBalance())
                .lastTrxDate(dto.getLastTrxDate())
                .lastSerialId(dto.getLastSerialId())
                .closeReasonId(dto.getCloseReasonId())
                .isLocked(dto.getIsLocked())
                .lockedBy(dto.getLockedBy())
                .createdOn(dto.getCreatedOn())
                .createdBy(dto.getCreatedBy())
                .modifiedOn(dto.getModifiedOn())
                .modifiedBy(dto.getModifiedBy())
                .build();
    }
}
