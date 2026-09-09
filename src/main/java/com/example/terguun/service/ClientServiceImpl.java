package com.example.terguun.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.terguun.dto.ClientDto;
import com.example.terguun.exception.ResourceNotFoundException;
import com.example.terguun.model.Client;
import com.example.terguun.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<ClientDto> getAll() {
        return clientRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto getById(Long id) {
        return toDto(findEntity(id));
    }

    @Override
    public ClientDto create(ClientDto dto) {
        Client client = toEntity(dto);
        client.setClientId(null);
        return toDto(clientRepository.save(client));
    }

    @Override
    public ClientDto update(Long id, ClientDto dto) {
        Client client = findEntity(id);
        Client updated = toEntity(dto).toBuilder().clientId(client.getClientId()).build();
        return toDto(clientRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        clientRepository.delete(findEntity(id));
    }

    private Client findEntity(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client олдсонгүй, id: " + id));
    }

    private ClientDto toDto(Client client) {
        return ClientDto.builder()
                .clientId(client.getClientId())
                .branchId(client.getBranchId())
                .clientType(client.getClientType())
                .clientName(client.getClientName())
                .directorName(client.getDirectorName())
                .familyName(client.getFamilyName())
                .firstName(client.getFirstName())
                .pinId(client.getPinId())
                .nationalId(client.getNationalId())
                .orgPinId(client.getOrgPinId())
                .birthDate(client.getBirthDate())
                .address1(client.getAddress1())
                .address2(client.getAddress2())
                .phone1(client.getPhone1())
                .phone2(client.getPhone2())
                .mobile(client.getMobile())
                .fax(client.getFax())
                .email(client.getEmail())
                .isStaff(client.getIsStaff())
                .isShareHolder(client.getIsShareHolder())
                .reminder(client.getReminder())
                .createdOn(client.getCreatedOn())
                .createdBy(client.getCreatedBy())
                .modifiedOn(client.getModifiedOn())
                .modifiedBy(client.getModifiedBy())
                .build();
    }

    private Client toEntity(ClientDto dto) {
        return Client.builder()
                .clientId(dto.getClientId())
                .branchId(dto.getBranchId())
                .clientType(dto.getClientType())
                .clientName(dto.getClientName())
                .directorName(dto.getDirectorName())
                .familyName(dto.getFamilyName())
                .firstName(dto.getFirstName())
                .pinId(dto.getPinId())
                .nationalId(dto.getNationalId())
                .orgPinId(dto.getOrgPinId())
                .birthDate(dto.getBirthDate())
                .address1(dto.getAddress1())
                .address2(dto.getAddress2())
                .phone1(dto.getPhone1())
                .phone2(dto.getPhone2())
                .mobile(dto.getMobile())
                .fax(dto.getFax())
                .email(dto.getEmail())
                .isStaff(dto.getIsStaff())
                .isShareHolder(dto.getIsShareHolder())
                .reminder(dto.getReminder())
                .createdOn(dto.getCreatedOn())
                .createdBy(dto.getCreatedBy())
                .modifiedOn(dto.getModifiedOn())
                .modifiedBy(dto.getModifiedBy())
                .build();
    }
}
