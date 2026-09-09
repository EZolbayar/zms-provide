package com.example.terguun.service;

import java.util.List;

import com.example.terguun.dto.LoanInstallmentDto;

public interface LoanInstallmentService {

    List<LoanInstallmentDto> getAll();

    LoanInstallmentDto getById(Long id);

    LoanInstallmentDto create(LoanInstallmentDto dto);

    LoanInstallmentDto update(Long id, LoanInstallmentDto dto);

    void delete(Long id);
}
