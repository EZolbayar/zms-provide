package com.example.terguun.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.terguun.dto.CustomerDto;
import com.example.terguun.exception.BadRequestException;
import com.example.terguun.exception.ResourceNotFoundException;
import com.example.terguun.model.Customer;
import com.example.terguun.repository.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomerService {

    /** CLIENTTYPE = "1": Монгол улсын иргэн (TBCLIENTS.ClientType bit = 1). */
    public static final String CLIENT_TYPE_CITIZEN = "1";
    /** CLIENTTYPE = "0": Хуулийн этгээд (TBCLIENTS.ClientType bit = 0). */
    public static final String CLIENT_TYPE_LEGAL_ENTITY = "0";

    // civilId нь Иргэний бүртгэлийн дугаар - 12 оронтой тоо байна (ж: 888954521912).
    private static final Pattern CIVIL_ID_PATTERN = Pattern.compile("^\\d{12}$");
    // pinId нь Регистрийн дугаар - эхний 2 үсэг нь кирилл ТОМ үсэг, дараа нь 8 цифр (ж: УБ90051234).
    private static final Pattern PIN_ID_PATTERN = Pattern.compile("^[А-ЯӨҮЁ]{2}\\d{8}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final CustomerRepository customerRepository;

    public List<CustomerDto> getAll() {
        return customerRepository.findAllByOrderByCreatedOnDesc().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getById(String customerId) {
        return toDto(customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Харилцагч олдсонгүй, id: " + customerId)));
    }

    /**
     * Шинэ харилцагчийг TBCUSTOMERS-д бүртгэнэ. CUSTOMERID нь TBCLIENTS.ClientID-ийн дугаарлалтыг
     * үргэлжлүүлэн гараар үүснэ (generateNextCustomerId харна уу).
     */
    @Transactional
    public CustomerDto create(CustomerDto request, String createdBy) {
        validate(request);

        String civilId = trimToNull(request.getCivilId());
        ensureCivilIdAvailable(civilId, null);

        LocalDateTime now = LocalDateTime.now();
        Customer customer = new Customer();
        customer.setCustomerId(generateNextCustomerId());
        applyFields(customer, request);
        customer.setCreatedOn(now);
        customer.setCreatedBy(createdBy);
        customer.setModifiedOn(now);
        customer.setModifiedBy(createdBy);

        Customer saved = customerRepository.save(customer);
        log.info("Шинэ харилцагч бүртгэгдлээ: customerId={}, civilId={}, createdBy={}",
                saved.getCustomerId(), saved.getCivilId(), createdBy);
        return toDto(saved);
    }

    /**
     * TBCLIENTS.ClientID-тэй ижил тэг-падтай дугаарлалтыг үргэлжлүүлнэ (ж: "00992" -> "00993").
     * Repository-ийн native query нь WITH (UPDLOCK, HOLDLOCK) hint ашиглан тухайн гүйлгээ дуустал
     * дугаар давхардуулахгүй байхыг баталгаажуулна.
     */
    private String generateNextCustomerId() {
        long next = 1L + Optional.ofNullable(customerRepository.findMaxNumericCustomerId()).orElse(0L);
        return String.format("%05d", next);
    }

    /** Одоо байгаа харилцагчийн мэдээллийг засна. Бүртгэсэн огноо/хэрэглэгч хэвээр үлдэнэ. */
    @Transactional
    public CustomerDto update(String customerId, CustomerDto request, String modifiedBy) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Харилцагч олдсонгүй, id: " + customerId));

        validate(request);

        String civilId = trimToNull(request.getCivilId());
        ensureCivilIdAvailable(civilId, customerId);

        applyFields(customer, request);
        customer.setModifiedOn(LocalDateTime.now());
        customer.setModifiedBy(modifiedBy);

        Customer saved = customerRepository.save(customer);
        log.info("Харилцагчийн мэдээлэл засварлагдлаа: customerId={}, civilId={}, modifiedBy={}",
                saved.getCustomerId(), saved.getCivilId(), modifiedBy);
        return toDto(saved);
    }

    /** Хүсэлтийн талбаруудыг тухайн entity дээр бичнэ. ID болон аудитын талбаруудад хамаарахгүй. */
    private void applyFields(Customer customer, CustomerDto request) {
        customer.setBranchId(trimToNull(request.getBranchId()));
        customer.setClientType(trimToNull(request.getClientType()));
        customer.setClientName(trimToNull(request.getClientName()));
        customer.setDirectorName(trimToNull(request.getDirectorName()));
        customer.setFamilyName(trimToNull(request.getFamilyName()));
        customer.setFirstName(trimToNull(request.getFirstName()));
        customer.setPinId(normalizePinId(request.getPinId()));
        customer.setNationalId(trimToNull(request.getNationalId()));
        customer.setOrgPinId(trimToNull(request.getOrgPinId()));
        customer.setBirthDate(request.getBirthDate());
        customer.setAddress1(trimToNull(request.getAddress1()));
        customer.setAddress2(trimToNull(request.getAddress2()));
        customer.setPhone1(trimToNull(request.getPhone1()));
        customer.setPhone2(trimToNull(request.getPhone2()));
        customer.setMobile(trimToNull(request.getMobile()));
        customer.setFax(trimToNull(request.getFax()));
        customer.setEmail(trimToNull(request.getEmail()));
        customer.setIsStaff(Boolean.TRUE.equals(request.getIsStaff()));
        customer.setIsShareHolder(Boolean.TRUE.equals(request.getIsShareHolder()));
        customer.setReminder(trimToNull(request.getReminder()));
        customer.setCivilId(trimToNull(request.getCivilId()));
        customer.setAimagCityName(trimToNull(request.getAimagCityName()));
        customer.setAimagCityCode(trimToNull(request.getAimagCityCode()));
        customer.setSoumDistrictName(trimToNull(request.getSoumDistrictName()));
        customer.setSoumDistrictCode(trimToNull(request.getSoumDistrictCode()));
        customer.setBagKhorooName(trimToNull(request.getBagKhorooName()));
        customer.setBagKhorooCode(trimToNull(request.getBagKhorooCode()));
        customer.setStreetName(trimToNull(request.getStreetName()));

        if (customer.getAddress1() == null) {
            customer.setAddress1(composeAddress(customer));
        }
    }

    /** civilId өөр харилцагчид аль хэдийн бүртгэлтэй эсэхийг шалгана. excludedCustomerId нь засварлаж буй харилцагчийг өөрийг нь алгасана. */
    private void ensureCivilIdAvailable(String civilId, String excludedCustomerId) {
        if (civilId == null) {
            return;
        }
        customerRepository.findByCivilId(civilId)
                .filter(existing -> !existing.getCustomerId().equals(excludedCustomerId))
                .ifPresent(existing -> {
                    throw new IllegalStateException("Энэ регистрийн дугаартай харилцагч аль хэдийн бүртгэгдсэн байна: " + civilId);
                });
    }

    private void validate(CustomerDto request) {
        String clientType = trimToNull(request.getClientType());
        if (clientType == null) {
            throw new BadRequestException("Харилцагчийн төрлийг сонгоно уу");
        }

        if (CLIENT_TYPE_LEGAL_ENTITY.equals(clientType)) {
            if (trimToNull(request.getClientName()) == null) {
                throw new BadRequestException("Хуулийн этгээдийн нэрийг оруулна уу");
            }
            if (trimToNull(request.getOrgPinId()) == null) {
                throw new BadRequestException("Хуулийн этгээдийн регистрийн дугаарыг оруулна уу");
            }
        } else if (CLIENT_TYPE_CITIZEN.equals(clientType)) {
            if (trimToNull(request.getFirstName()) == null) {
                throw new BadRequestException("Харилцагчийн нэрийг оруулна уу");
            }
            if (trimToNull(request.getFamilyName()) == null) {
                throw new BadRequestException("Овгийг оруулна уу");
            }
            if (request.getBirthDate() == null) {
                throw new BadRequestException("Төрсөн огноог оруулна уу");
            }
            String civilId = trimToNull(request.getCivilId());
            if (civilId == null) {
                throw new BadRequestException("Иргэний бүртгэлийн дугаарыг оруулна уу");
            }
            if (!CIVIL_ID_PATTERN.matcher(civilId).matches()) {
                throw new BadRequestException("Иргэний бүртгэлийн дугаар буруу байна. 12 оронтой тоо байна (ж: 888954521912)");
            }

            String pinId = normalizePinId(request.getPinId());
            if (pinId == null) {
                throw new BadRequestException("Регистрийн дугаарыг оруулна уу");
            }
            if (!PIN_ID_PATTERN.matcher(pinId).matches()) {
                throw new BadRequestException("Регистрийн дугаар буруу байна. Эхний 2 үсэг кирилл том үсэг, дараа нь 8 оронтой тоо байна (ж: УБ90051234)");
            }
        } else {
            throw new BadRequestException("Харилцагчийн төрөл буруу байна: " + clientType);
        }

        // Хаягийн нэрүүд иргэн, хуулийн этгээд хоёуланд заавал. Кодыг ЗМС-ийн хавсралтаас тохируулна.
        if (trimToNull(request.getAimagCityName()) == null) {
            throw new BadRequestException("Аймаг / хотыг оруулна уу");
        }
        if (trimToNull(request.getSoumDistrictName()) == null) {
            throw new BadRequestException("Сум / дүүргийг оруулна уу");
        }
        if (trimToNull(request.getBagKhorooName()) == null) {
            throw new BadRequestException("Баг / хороог оруулна уу");
        }

        String email = trimToNull(request.getEmail());
        if (email != null && !EMAIL_PATTERN.matcher(email).matches()) {
            throw new BadRequestException("И-мэйл хаяг буруу байна: " + email);
        }
    }

    /** ADDRESS1 хоосон үед задалсан хаягийн багануудаас бүтэн хаягийг угсарна. */
    private String composeAddress(Customer customer) {
        String composed = Stream
                .of(customer.getAimagCityName(), customer.getSoumDistrictName(), customer.getBagKhorooName(),
                        customer.getStreetName())
                .filter(part -> part != null && !part.isBlank())
                .collect(Collectors.joining(" "));
        return composed.isBlank() ? null : composed;
    }

    /** Регистрийн дугаарын үсгийг том болгож (ө -> Ө, ү -> Ү г.м) хадгална, ингэснээр жижиг үсгээр оруулсан ч зөв болно. */
    private static String normalizePinId(String value) {
        String trimmed = trimToNull(value);
        return trimmed == null ? null : trimmed.toUpperCase(Locale.ROOT);
    }

    private static String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    private CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .branchId(customer.getBranchId())
                .clientType(customer.getClientType())
                .clientName(customer.getClientName())
                .directorName(customer.getDirectorName())
                .familyName(customer.getFamilyName())
                .firstName(customer.getFirstName())
                .pinId(customer.getPinId())
                .nationalId(customer.getNationalId())
                .orgPinId(customer.getOrgPinId())
                .birthDate(customer.getBirthDate())
                .address1(customer.getAddress1())
                .address2(customer.getAddress2())
                .phone1(customer.getPhone1())
                .phone2(customer.getPhone2())
                .mobile(customer.getMobile())
                .fax(customer.getFax())
                .email(customer.getEmail())
                .isStaff(customer.getIsStaff())
                .isShareHolder(customer.getIsShareHolder())
                .reminder(customer.getReminder())
                .civilId(customer.getCivilId())
                .aimagCityName(customer.getAimagCityName())
                .aimagCityCode(customer.getAimagCityCode())
                .soumDistrictName(customer.getSoumDistrictName())
                .soumDistrictCode(customer.getSoumDistrictCode())
                .bagKhorooName(customer.getBagKhorooName())
                .bagKhorooCode(customer.getBagKhorooCode())
                .streetName(customer.getStreetName())
                .createdOn(customer.getCreatedOn())
                .createdBy(customer.getCreatedBy())
                .modifiedOn(customer.getModifiedOn())
                .modifiedBy(customer.getModifiedBy())
                .build();
    }
}
