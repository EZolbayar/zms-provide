package com.example.terguun.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.example.terguun.dto.CollateralDetailDto;
import com.example.terguun.exception.ResourceNotFoundException;
import com.example.terguun.model.CollateralDetail;
import com.example.terguun.repository.CollateralDetailRepository;

@Service
@RequiredArgsConstructor
public class CollateralDetailServiceImpl implements CollateralDetailService {

    private final CollateralDetailRepository collateralDetailRepository;

    @Override
    public List<CollateralDetailDto> getAll() {
        return collateralDetailRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CollateralDetailDto getById(Long id) {
        return toDto(findEntity(id));
    }

    @Override
    public CollateralDetailDto create(CollateralDetailDto dto) {
        CollateralDetail collateralDetail = toEntity(dto);
        collateralDetail.setSerialId(null);
        return toDto(collateralDetailRepository.save(collateralDetail));
    }

    @Override
    public CollateralDetailDto update(Long id, CollateralDetailDto dto) {
        CollateralDetail collateralDetail = findEntity(id);
        CollateralDetail updated = toEntity(dto).toBuilder().serialId(collateralDetail.getSerialId()).build();
        return toDto(collateralDetailRepository.save(updated));
    }

    @Override
    public void delete(Long id) {
        collateralDetailRepository.delete(findEntity(id));
    }

    private CollateralDetail findEntity(Long id) {
        return collateralDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CollateralDetail олдсонгүй, id: " + id));
    }

    private CollateralDetailDto toDto(CollateralDetail collateralDetail) {
        return CollateralDetailDto.builder()
                .serialId(collateralDetail.getSerialId())
                .branchId(collateralDetail.getBranchId())
                .clientId(collateralDetail.getClientId())
                .collateralTypeId(collateralDetail.getCollateralTypeId())
                .collateralName(collateralDetail.getCollateralName())
                .registrationId(collateralDetail.getRegistrationId())
                .licenseId(collateralDetail.getLicenseId())
                .registrationOrgName(collateralDetail.getRegistrationOrgName())
                .size(collateralDetail.getSize())
                .location(collateralDetail.getLocation())
                .description(collateralDetail.getDescription())
                .makeDate(collateralDetail.getMakeDate())
                .coFounder(collateralDetail.getCoFounder())
                .coFoundersName(collateralDetail.getCoFoundersName())
                .manufacture(collateralDetail.getManufacture())
                .shaftId(collateralDetail.getShaftId())
                .dedication(collateralDetail.getDedication())
                .mark(collateralDetail.getMark())
                .color(collateralDetail.getColor())
                .valuer(collateralDetail.getValuer())
                .valuedDate(collateralDetail.getValuedDate())
                .collateralValue(collateralDetail.getCollateralValue())
                .marketValue(collateralDetail.getMarketValue())
                .forcedSaleValue(collateralDetail.getForcedSaleValue())
                .createdOn(collateralDetail.getCreatedOn())
                .createdBy(collateralDetail.getCreatedBy())
                .modifiedOn(collateralDetail.getModifiedOn())
                .modifiedBy(collateralDetail.getModifiedBy())
                .build();
    }

    private CollateralDetail toEntity(CollateralDetailDto dto) {
        return CollateralDetail.builder()
                .serialId(dto.getSerialId())
                .branchId(dto.getBranchId())
                .clientId(dto.getClientId())
                .collateralTypeId(dto.getCollateralTypeId())
                .collateralName(dto.getCollateralName())
                .registrationId(dto.getRegistrationId())
                .licenseId(dto.getLicenseId())
                .registrationOrgName(dto.getRegistrationOrgName())
                .size(dto.getSize())
                .location(dto.getLocation())
                .description(dto.getDescription())
                .makeDate(dto.getMakeDate())
                .coFounder(dto.getCoFounder())
                .coFoundersName(dto.getCoFoundersName())
                .manufacture(dto.getManufacture())
                .shaftId(dto.getShaftId())
                .dedication(dto.getDedication())
                .mark(dto.getMark())
                .color(dto.getColor())
                .valuer(dto.getValuer())
                .valuedDate(dto.getValuedDate())
                .collateralValue(dto.getCollateralValue())
                .marketValue(dto.getMarketValue())
                .forcedSaleValue(dto.getForcedSaleValue())
                .createdOn(dto.getCreatedOn())
                .createdBy(dto.getCreatedBy())
                .modifiedOn(dto.getModifiedOn())
                .modifiedBy(dto.getModifiedBy())
                .build();
    }
}
