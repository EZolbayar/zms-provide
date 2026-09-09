package com.example.terguun.service;

import java.util.List;

import com.example.terguun.dto.ClientDto;

public interface ClientService {

    List<ClientDto> getAll();

    ClientDto getById(Long id);

    ClientDto create(ClientDto dto);

    ClientDto update(Long id, ClientDto dto);

    void delete(Long id);
}
