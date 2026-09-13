package com.example.terguun.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.terguun.dto.LoanInstallmentDto;
import com.example.terguun.exception.ResourceNotFoundException;
import com.example.terguun.model.LoanInstallment;
import com.example.terguun.repository.LoanInstallmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoanInstallmentService {

    private final LoanInstallmentRepository loanInstallmentRepository;

    public List<LoanInstallmentDto> getAll() {
        return loanInstallmentRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public LoanInstallmentDto getById(String accountId) {
        log.info("Fetching LoanInstallment for accountId: {}", accountId);
        return toDto(findEntity(accountId));
    }

    private LoanInstallment findEntity(String accountId) {
        return loanInstallmentRepository.findByAccountIdIn(List.of(accountId)).stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("LoanInstallment олдсонгүй, id: " + accountId));
    }

    private LoanInstallmentDto toDto(LoanInstallment loanInstallment) {
        return LoanInstallmentDto.builder()
                .installmentId(loanInstallment.getInstallmentId())
                .branchId(loanInstallment.getBranchId())
                .accountId(loanInstallment.getAccountId())
                .isActive(loanInstallment.getIsActive())
                .dueDate(loanInstallment.getDueDate())
                .principal(loanInstallment.getPrincipal())
                .interest(loanInstallment.getInterest())
                .originalPrincipal(loanInstallment.getOriginalPrincipal())
                .afterBalance(loanInstallment.getAfterBalance())
                .beforeBalance(loanInstallment.getBeforeBalance())
                .installmentFlag(loanInstallment.getInstallmentFlag())
                .createdOn(loanInstallment.getCreatedOn())
                .createdBy(loanInstallment.getCreatedBy())
                .modifiedOn(loanInstallment.getModifiedOn())
                .modifiedBy(loanInstallment.getModifiedBy())
                .build();
    }

    private LoanInstallment toEntity(LoanInstallmentDto dto) {
        return LoanInstallment.builder()
                .installmentId(dto.getInstallmentId())
                .branchId(dto.getBranchId())
                .accountId(dto.getAccountId())
                .isActive(dto.getIsActive())
                .dueDate(dto.getDueDate())
                .principal(dto.getPrincipal())
                .interest(dto.getInterest())
                .originalPrincipal(dto.getOriginalPrincipal())
                .afterBalance(dto.getAfterBalance())
                .beforeBalance(dto.getBeforeBalance())
                .installmentFlag(dto.getInstallmentFlag())
                .createdOn(dto.getCreatedOn())
                .createdBy(dto.getCreatedBy())
                .modifiedOn(dto.getModifiedOn())
                .modifiedBy(dto.getModifiedBy())
                .build();
    }
}
