package com.example.terguun.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.terguun.model.AddressMapping;
import com.example.terguun.model.Customer;
import com.example.terguun.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Эхлэх үед TBCUSTOMERS-ийн хаягийн задалсан багануудыг нөхнө, ингэснээр ЗМС рүү илгээх хаяг бүхэлдээ
 * TBCUSTOMERS-ээс уншигдана.
 *
 * - TBCLIENTS-ээс миграцлагдсан мөрүүдэд нэр хоосон тул ADDRESS1-ийг задалж хоосон нэрсийг бөглөнө.
 * - Аймаг/хот, сум/дүүргийн кодыг ADDRESS_MAPPING-ийн ХУР (*_XYP) кодоор бөглөнө. Үндсэн код (ж: "21060")
 *   хадгалагдсан мөрүүдийг ХУР код руу сольно.
 *
 * Код нь аль хэдийн ХУР код бол мөрийг алгасна, тиймээс дахин ажиллахад юу ч өөрчлөхгүй.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class CustomerAddressBackfill implements ApplicationRunner {

    // "<аймаг/хот> аймаг|хот <сум/дүүрэг> сум|дүүрэг <баг>-р баг <гудамж> <байр>" хэлбэрийн хаягийг задлана.
    private static final Pattern ADDRESS_PATTERN = Pattern.compile(
            "^(?<aimag>.+?(?:аймаг|хот))\\s+(?<soum>.+?(?:сум|дүүрэг))\\s+(?<bag>.+?(?:баг|хороо))\\s+(?<street>\\S+)\\s+(?<apartment>.+)$");

    private final CustomerRepository customerRepository;
    private final AddressMappingService addressMappingService;

    @Override
    public void run(ApplicationArguments args) {
        List<Customer> changed = new ArrayList<>();
        for (Customer customer : customerRepository.findAll()) {
            if (fill(customer)) {
                changed.add(customer);
            }
        }
        if (!changed.isEmpty()) {
            customerRepository.saveAll(changed);
        }
        log.info("TBCUSTOMERS хаягийн нөхөлт: {} мөр шинэчлэгдлээ", changed.size());
    }

    private boolean fill(Customer customer) {
        boolean needsCodes = isBlank(customer.getAimagCityCode()) || isBlank(customer.getSoumDistrictCode())
                || addressMappingService.isDistrictCode(customer.getSoumDistrictCode());
        if (!needsCodes) {
            return false;
        }

        boolean changed = false;
        Matcher matcher = customer.getAddress1() == null ? null : ADDRESS_PATTERN.matcher(customer.getAddress1().trim());
        if (matcher != null && matcher.matches()) {
            changed |= setIfBlank(customer.getAimagCityName(), matcher.group("aimag"), customer::setAimagCityName);
            changed |= setIfBlank(customer.getSoumDistrictName(), matcher.group("soum"), customer::setSoumDistrictName);
            changed |= setIfBlank(customer.getBagKhorooName(), matcher.group("bag"), customer::setBagKhorooName);
            changed |= setIfBlank(customer.getStreetName(), matcher.group("street"), customer::setStreetName);
            changed |= setIfBlank(customer.getAddress2(), matcher.group("apartment"), customer::setAddress2);
        }

        Optional<AddressMapping> mapping = addressMappingService.resolve(customer.getSoumDistrictCode(),
                customer.getAimagCityName(), customer.getSoumDistrictName());
        if (mapping.isPresent()) {
            AddressMapping row = mapping.get();
            changed |= replace(customer.getAimagCityCode(), row.getCityCodeXyp(), customer::setAimagCityCode);
            changed |= replace(customer.getSoumDistrictCode(), row.getDistrictCodeXyp(), customer::setSoumDistrictCode);
            changed |= setIfBlank(customer.getBagKhorooCode(), row.getBagkhorooCodeXyp(), customer::setBagKhorooCode);
        }
        return changed;
    }

    private static boolean setIfBlank(String current, String value, Consumer<String> setter) {
        if (!isBlank(current) || isBlank(value)) {
            return false;
        }
        setter.accept(value.trim());
        return true;
    }

    private static boolean replace(String current, String value, Consumer<String> setter) {
        if (isBlank(value) || value.trim().equals(current)) {
            return false;
        }
        setter.accept(value.trim());
        return true;
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
