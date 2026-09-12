package com.example.terguun.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.terguun.dto.LoanInstallmentDto;
import com.example.terguun.exception.ResourceNotFoundException;
import com.example.terguun.model.LoanInstallment;
import com.example.terguun.repository.LoanInstallmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanInstallmentService {

    private final LoanInstallmentRepository loanInstallmentRepository;

    public List<LoanInstallmentDto> getAll() {
        return loanInstallmentRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public LoanInstallmentDto getById(Long id) {
        return toDto(findEntity(id));
    }

    public LoanInstallmentDto create(LoanInstallmentDto dto) {
        LoanInstallment loanInstallment = toEntity(dto);
        loanInstallment.setInstallmentId(null);
        return toDto(loanInstallmentRepository.save(loanInstallment));
    }

    public LoanInstallmentDto update(Long id, LoanInstallmentDto dto) {
        LoanInstallment loanInstallment = findEntity(id);
        LoanInstallment updated = toEntity(dto).toBuilder().installmentId(loanInstallment.getInstallmentId()).build();
        return toDto(loanInstallmentRepository.save(updated));
    }

    public void delete(Long id) {
        loanInstallmentRepository.delete(findEntity(id));
    }

    private LoanInstallment findEntity(Long id) {
        return loanInstallmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LoanInstallment олдсонгүй, id: " + id));
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
