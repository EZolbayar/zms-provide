package com.example.terguun.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.terguun.model.AddressMapping;

public interface AddressMappingRepository extends JpaRepository<AddressMapping, String> {
}
