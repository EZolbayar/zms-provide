package com.example.terguun.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.terguun.dto.UploadLogDto;
import com.example.terguun.model.Customer;
import com.example.terguun.model.SainUploadLog;
import com.example.terguun.repository.CustomerRepository;
import com.example.terguun.repository.SainUploadLogRepository;

import lombok.RequiredArgsConstructor;

/** ЗМС рүү илгээсэн түүхийг (TBSAINUPLOADLOG) хэрэглэгчид харуулна. */
@Service
@RequiredArgsConstructor
public class UploadLogService {

    /** Огноо заагаагүй бол сүүлийн 7 хоногийг харуулна. */
    private static final int DEFAULT_DAYS = 7;

    private final SainUploadLogRepository uploadLogRepository;
    private final CustomerRepository customerRepository;

    /** from, to нь хоёулаа хамааруулсан өдрүүд (to өдрийн төгсгөл хүртэл). */
    public List<UploadLogDto> getLogs(LocalDate from, LocalDate to) {
        LocalDate end = to == null ? LocalDate.now() : to;
        LocalDate start = from == null ? end.minusDays(DEFAULT_DAYS - 1) : from;

        List<SainUploadLog> logs = uploadLogRepository
                .findByUploadedOnGreaterThanEqualAndUploadedOnLessThanOrderByUploadedOnDesc(
                        start.atStartOfDay(), end.plusDays(1).atStartOfDay());

        List<String> clientIds = logs.stream().map(SainUploadLog::getClientId).filter(Objects::nonNull)
                .distinct().toList();
        Map<String, Customer> customers = customerRepository.findAllById(clientIds).stream()
                .collect(Collectors.toMap(Customer::getCustomerId, Function.identity()));

        return logs.stream().map(log -> toDto(log, customers.get(log.getClientId()))).toList();
    }

    private UploadLogDto toDto(SainUploadLog log, Customer customer) {
        return UploadLogDto.builder()
                .logId(log.getLogId())
                .uploadedOn(log.getUploadedOn())
                .uploadedBy(log.getUploadedBy())
                .clientId(log.getClientId())
                .customerName(customerName(customer))
                .accountId(log.getAccountId())
                .changeType(log.getChangeType())
                .customerType(log.getCustomerType())
                .endpoint(log.getEndpoint())
                .patchNumber(log.getPatchNumber())
                .success(log.getSuccess())
                .errorMessage(log.getErrorMessage())
                .build();
    }

    /** Иргэн бол "Овог Нэр" (firstName + clientName), хуулийн этгээд бол байгууллагын нэр. */
    private static String customerName(Customer customer) {
        if (customer == null) {
            return null;
        }
        if (CustomerService.CLIENT_TYPE_CITIZEN.equals(customer.getClientType()) && customer.getFirstName() != null) {
            return customer.getFirstName().trim() + " " + Objects.toString(customer.getClientName(), "").trim();
        }
        return customer.getClientName();
    }
}
