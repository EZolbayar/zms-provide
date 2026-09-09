package com.example.terguun.service;

import java.util.List;

import com.example.terguun.dto.AccountDto;

public interface AccountService {

    List<AccountDto> getAll();

    AccountDto getById(Long id);

    AccountDto create(AccountDto dto);

    AccountDto update(Long id, AccountDto dto);

    void delete(Long id);
}
