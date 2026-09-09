package com.example.terguun.service;

import java.util.List;

import com.example.terguun.dto.CollateralDetailDto;

public interface CollateralDetailService {

    List<CollateralDetailDto> getAll();

    CollateralDetailDto getById(Long id);

    CollateralDetailDto create(CollateralDetailDto dto);

    CollateralDetailDto update(Long id, CollateralDetailDto dto);

    void delete(Long id);
}
