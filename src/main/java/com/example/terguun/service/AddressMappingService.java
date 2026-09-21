package com.example.terguun.service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.terguun.model.AddressMapping;
import com.example.terguun.repository.AddressMappingRepository;

import lombok.RequiredArgsConstructor;

/**
 * Хаягийн лавлах. 350 мөр бөгөөд өөрчлөгддөггүй тул анх хэрэглэгдэх үед санах ойд нэг удаа ачаалж
 * хадгална — ЗМС рүү илгээх бүрд DB-ээс дахин татахгүй.
 */
@Service
@RequiredArgsConstructor
public class AddressMappingService {

    private final AddressMappingRepository repository;

    private volatile List<AddressMapping> cache;
    private volatile Map<String, AddressMapping> byDistrictCode;
    private volatile Map<String, AddressMapping> byNames;

    /** Формын сонголтод: хотын кодоор, дараа нь дүүргийн нэрээр эрэмбэлсэн бүх мөр. */
    public List<AddressMapping> getAll() {
        ensureLoaded();
        return cache;
    }

    /**
     * Харилцагчийн хаягт тохирох мөрийг олно: эхлээд хадгалсан дүүргийн кодоор, олдохгүй бол аймаг/хот
     * болон сум/дүүргийн нэрээр (TBCLIENTS-ээс миграцлагдсан мөрүүдэд код байхгүй, зөвхөн нэр байдаг).
     */
    public Optional<AddressMapping> resolve(String districtCode, String cityName, String districtName) {
        ensureLoaded();
        if (districtCode != null && !districtCode.isBlank()) {
            AddressMapping row = byDistrictCode.get(districtCode.trim());
            if (row != null) {
                return Optional.of(row);
            }
        }
        if (cityName == null || districtName == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(byNames.get(nameKey(cityName, districtName)));
    }

    /** Аймаг/хот болон сум/дүүргийн нэрээр лавлахын мөрийг олно. */
    public Optional<AddressMapping> findByNames(String cityName, String districtName) {
        return resolve(null, cityName, districtName);
    }

    /** Лавлахын үндсэн DISTRICT_CODE мөн эсэх (ХУР код биш, ж: "21060"). */
    public boolean isDistrictCode(String code) {
        ensureLoaded();
        return code != null && byDistrictCode.containsKey(code.trim());
    }

    private void ensureLoaded() {
        if (cache != null) {
            return;
        }
        synchronized (this) {
            if (cache != null) {
                return;
            }
            List<AddressMapping> rows = repository.findAll().stream()
                    .sorted(Comparator.comparing(AddressMapping::getCityCode, Comparator.nullsLast(String::compareTo))
                            .thenComparing(AddressMapping::getDistrictName, Comparator.nullsLast(String::compareTo)))
                    .toList();
            byDistrictCode = rows.stream()
                    .collect(Collectors.toMap(AddressMapping::getDistrictCode, Function.identity(), (a, b) -> a));
            byNames = rows.stream()
                    .filter(row -> row.getCityName() != null && row.getDistrictName() != null
                            && !row.getDistrictName().isBlank())
                    .collect(Collectors.toMap(row -> nameKey(row.getCityName(), row.getDistrictName()),
                            Function.identity(), (a, b) -> a));
            cache = rows;
        }
    }

    private static String nameKey(String cityName, String districtName) {
        return normalize(cityName) + "|" + normalize(districtName);
    }

    private static String normalize(String value) {
        return value.trim().replaceAll("\s+", " ").toLowerCase(Locale.ROOT);
    }
}
