package com.example.terguun.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.terguun.dto.RecentCustomerData;
import com.example.terguun.dto.RecentCustomerData.ChangeType;
import com.example.terguun.dto.RecentUploadItem;
import com.example.terguun.dto.SainUploadPayload;
import com.example.terguun.dto.sain.CustomerAddress;
import com.example.terguun.dto.sain.CustomerBankRelation;
import com.example.terguun.dto.sain.CustomerData;
import com.example.terguun.dto.sain.EntityAddress;
import com.example.terguun.dto.sain.EntityData;
import com.example.terguun.dto.sain.OrgCeo;
import com.example.terguun.dto.sain.OrgRate;
import com.example.terguun.dto.sain.LoanInformation;
import com.example.terguun.dto.sain.LoanPayment;
import com.example.terguun.dto.sain.LoanSchedule;
import com.example.terguun.dto.sain.LoanTransactions;
import com.example.terguun.exception.ResourceNotFoundException;
import com.example.terguun.model.Account;
import com.example.terguun.model.Customer;
import com.example.terguun.model.LoanInstallment;
import com.example.terguun.model.SainUploadLog;
import com.example.terguun.repository.AccountRepository;
import com.example.terguun.repository.CustomerRepository;
import com.example.terguun.repository.LoanInstallmentRepository;
import com.example.terguun.repository.SainUploadLogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class RecentlyDataService {

        // "<аймаг/хот> аймаг|хот <сум/дүүрэг> сум|дүүрэг <баг>-р баг <гудамж> <байр>" хэлбэрийн хаягийг задлана.
        private static final Pattern ADDRESS_PATTERN = Pattern.compile(
                        "^(?<aimag>.+?(?:аймаг|хот))\\s+(?<soum>.+?(?:сум|дүүрэг))\\s+(?<bag>.+?(?:баг|хороо))\\s+(?<street>\\S+)\\s+(?<apartment>.+)$");

        /** TBACCOUNTS.AccountStatus-ийн хаагдсан дансны утга. */
        private static final String ACCOUNT_STATUS_CLOSED = "C";

        /** TBACCOUNTS.AccountStatus-ийн идэвхтэй дансны утга. */
        private static final String ACCOUNT_STATUS_ACTIVE = "A";

        // Протокол 10.2: YYYY-MM-DD HH:mm:ss.SSSSSS
        private static final DateTimeFormatter PAYMENT_DATE_FORMAT = DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        // Протоколын хавсралтын кодууд (Annex 5, 6, 7, 8, 9, 10, 11, 12) эх сангийн хүснэгтүүдэд байхгүй тул
        // тохиргооноос авна. Утгуудыг application.properties-д ЗМС-ийн хавсралтын дагуу тохируулна.
        @Value("${sain.loan.provenance}")
        private String loanProvenance;

        @Value("${sain.loan.currency}")
        private String loanCurrency;

        @Value("${sain.loan.sector}")
        private String loanSector;

        @Value("${sain.loan.class}")
        private String loanClass;

        @Value("${sain.loan.type}")
        private String loanTypeCode;

        @Value("${sain.loan.schedule-type}")
        private String loanScheduleType;

        @Value("${sain.loan.status.active}")
        private String loanStatusActive;

        @Value("${sain.loan.status.closed}")
        private String loanStatusClosed;

        @Value("${sain.loan.decide-status}")
        private String loanDecideStatus;

        // Хуулийн этгээдийн Annex 16 (ажиллагсдын тоо), Annex 1 (хариуцлагын хэлбэр), Annex 2 (үнэлгээ хийсэн
        // байгууллага) утгууд. TBCUSTOMERS-д багана байхгүй тул тохиргооноос авна.
        @Value("${sain.org.numof-employee}")
        private String orgNumOfEmployee;

        @Value("${sain.org.company-type}")
        private String orgCompanyType;

        @Value("${sain.org.rate-agency}")
        private String orgRateAgency;

        @Value("${sain.org.rating}")
        private String orgRateValue;

        @Value("${sain.customer.bank-relation}")
        private String customerBankRelation;

        @Value("${look.back.hours}")
        private int lookbackHours;

        private final CustomerRepository customerRepository;
        private final AccountRepository accountRepository;
        private final LoanInstallmentRepository loanInstallmentRepository;
        private final SainUploadLogRepository uploadLogRepository;

        /**
         * Нийлүүлэх мэдээллийн нэгдсэн жагсаалт: сүүлийн lookbackHours цагт үүссэн шинэ зээл, хаагдсан
         * зээл, эргэн төлөлт гурвыг нэг дуудалтаар, төрлөөр нь ялгаж буцаана. Нэг харилцагч өөр өөр
         * төрлөөр давхар орж болно (ж: шинэ зээл авчихаад тэр дороо төлөлт хийсэн).
         *
         * Жагсаалт нь зөвхөн дэлгэцэд харуулах хураангуйг буцаана — ЗМС рүү илгээх бүтэн payload нь
         * хэдэн МБ хүрдэг тул илгээх товч дарахад сонгосон мөрүүд дээр л угсарна (buildForUpload).
         * Ингэснээр жагсаалт нь дансны 3 шүүлт + харилцагчдыг багцлан татах 1, нийт 4 асуулгаар бүрдэнэ.
         */
        public List<RecentCustomerData> buildForRecentChanges() {
                LocalDateTime since = LocalDateTime.now().minusHours(lookbackHours);

                List<Account> newAccounts = accountRepository.findByCreatedOnAfter(since);
                List<Account> closedAccounts = accountRepository.findByModifiedOnAfterAndAccountStatusAndBalance(since,
                                ACCOUNT_STATUS_CLOSED, BigDecimal.ZERO);
                List<Account> repaidAccounts = accountRepository.findRepaidAccounts(since, ACCOUNT_STATUS_ACTIVE);
                log.info("Сүүлийн {} цагийн өөрчлөлт: шинэ={}, хаагдсан={}, эргэн төлөлт={}",
                                lookbackHours, newAccounts.size(), closedAccounts.size(), repaidAccounts.size());

                List<MatchedAccount> matched = new ArrayList<>();
                matched.addAll(latestAccountPerClient(newAccounts, ChangeType.NEW_LOAN));
                matched.addAll(latestAccountPerClient(closedAccounts, ChangeType.CLOSED_LOAN));
                matched.addAll(latestAccountPerClient(repaidAccounts, ChangeType.REPAYMENT));

                List<MatchedAccount> pending = excludeUploaded(matched);

                Map<String, Customer> customers = loadCustomers(
                                pending.stream().map(MatchedAccount::clientId).collect(Collectors.toSet()));

                return pending.stream()
                                .map(row -> toSummary(row, customers.get(row.clientId())))
                                .collect(Collectors.toList());
        }

        /**
         * Жагсаалтаас сонгосон мөрүүдийн ЗМС payload-ыг угсарна. Харилцагч, данс, зээлийн хуваарийг тус
         * бүр нэг асуулгаар багцлан татдаг тул мөрийн тоо хэдэн зуу байлаа ч нийт 3 асуулгаар шийднэ.
         *
         * TBCUSTOMERS.CLIENTTYPE = "0" (хуулийн этгээд) бол /upload-entity-ийн бүтцээр, бусад тохиолдолд
         * иргэний /upload-citizen-ийн бүтцээр угсарна. Хариу нь оролтын дарааллаа хадгална.
         */
        public List<SainUploadPayload> buildForUpload(List<RecentUploadItem> items) {
                if (items == null || items.isEmpty()) {
                        return List.of();
                }

                Set<String> clientIds = items.stream().map(RecentUploadItem::getClientId)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toCollection(LinkedHashSet::new));
                Set<String> accountIds = items.stream().map(RecentUploadItem::getAccountId)
                                .filter(Objects::nonNull)
                                .collect(Collectors.toCollection(LinkedHashSet::new));

                Map<String, Customer> customers = loadCustomers(clientIds);
                Map<String, Account> accounts = accountRepository.findAllById(accountIds).stream()
                                .collect(Collectors.toMap(Account::getAccountId, Function.identity(), (a, b) -> a));
                Map<String, List<LoanInstallment>> installments = loadInstallments(accountIds);

                return items.stream().map(item -> {
                        Customer customer = requireCustomer(item.getClientId(), customers);
                        Account loanAccount = accounts.get(item.getAccountId());
                        List<LoanInstallment> schedule = installments.getOrDefault(item.getAccountId(), List.of());
                        boolean legalEntity = isLegalEntity(customer);
                        return new SainUploadPayload(
                                        legalEntity ? null : buildCustomerData(customer, loanAccount, schedule, true),
                                        legalEntity ? buildEntityData(customer, loanAccount, schedule) : null,
                                        item.getClientId(),
                                        item.getAccountId(),
                                        item.getChangeType(),
                                        customer.getClientType(),
                                        loanAccount == null ? null : loanAccount.getModifiedOn());
                }).collect(Collectors.toList());
        }

        /** TBCUSTOMERS.CLIENTTYPE = "0" бол хуулийн этгээд (CustomerService.CLIENT_TYPE_LEGAL_ENTITY). */
        private boolean isLegalEntity(Customer customer) {
                return CustomerService.CLIENT_TYPE_LEGAL_ENTITY.equals(customer.getClientType());
        }

        /**
         * Харилцагчийн дугаараар (POST /api/send-data) гараар татах. Өөрчлөлтийн төрөл хамаарахгүй тул
         * идэвхтэй данснуудаас, тэдгээр байхгүй бол хамгийн сүүлд нээгдсэн дансыг сонгоно.
         */
        public List<CustomerData> buildForClients(List<String> clientIds) {
                List<String> distinctIds = clientIds.stream().distinct().collect(Collectors.toList());
                if (distinctIds.isEmpty()) {
                        return List.of();
                }

                Map<String, Customer> customers = loadCustomers(distinctIds);
                Map<String, List<Account>> accountsByClient = accountRepository.findByClientIdIn(distinctIds).stream()
                                .collect(Collectors.groupingBy(Account::getClientId));

                // Аль данснуудын хуваарь хэрэгтэйг эхлээд тодорхойлоод, дараа нь нэг асуулгаар цуг татна.
                Map<String, List<Account>> scheduleAccountsByClient = new LinkedHashMap<>();
                Set<String> scheduleAccountIds = new LinkedHashSet<>();
                for (String clientId : distinctIds) {
                        List<Account> accounts = accountsByClient.getOrDefault(clientId, List.of());
                        List<Account> activeAccounts = accounts.stream()
                                        .filter(account -> ACCOUNT_STATUS_ACTIVE.equals(account.getAccountStatus()))
                                        .collect(Collectors.toList());
                        List<Account> scheduleAccounts = activeAccounts.isEmpty()
                                        ? latestAccount(accounts).map(List::of).orElse(List.of())
                                        : activeAccounts;
                        scheduleAccountsByClient.put(clientId, scheduleAccounts);
                        scheduleAccounts.forEach(account -> scheduleAccountIds.add(account.getAccountId()));
                }
                Map<String, List<LoanInstallment>> installments = loadInstallments(scheduleAccountIds);

                return distinctIds.stream().map(clientId -> {
                        List<Account> accounts = accountsByClient.getOrDefault(clientId, List.of());
                        List<Account> scheduleAccounts = scheduleAccountsByClient.getOrDefault(clientId, List.of());
                        Account loanAccount = latestAccount(scheduleAccounts).orElse(null);
                        List<LoanInstallment> clientInstallments = scheduleAccounts.stream()
                                        .flatMap(account -> installments
                                                        .getOrDefault(account.getAccountId(), List.<LoanInstallment>of())
                                                        .stream())
                                        .collect(Collectors.toList());
                        return buildCustomerData(requireCustomer(clientId, customers), loanAccount, clientInstallments,
                                        !accounts.isEmpty());
                }).collect(Collectors.toList());
        }


        /**
         * ЗМС рүү амжилттай илгээгдсэн мөрүүдийг жагсаалтаас хасна.
         *
         * Шинэ зээл, зээл хаах нь нэг удаагийн үйл явдал тул амжилттай илгээгдсэн бол дахин гарахгүй.
         * Эргэн төлөлт харин давтагддаг тул илгээсэн цагаас хойш данс дахин өөрчлөгдсөн (шинэ төлөлт
         * орсон) бол дахин жагсаалтад гарч ирнэ.
         */
        private List<MatchedAccount> excludeUploaded(List<MatchedAccount> matched) {
                if (matched.isEmpty()) {
                        return matched;
                }
                Set<String> accountIds = matched.stream()
                                .map(row -> row.account().getAccountId())
                                .collect(Collectors.toCollection(LinkedHashSet::new));

                Map<String, LocalDateTime> lastSuccess = new LinkedHashMap<>();
                for (SainUploadLog uploadLog : uploadLogRepository.findBySuccessTrueAndAccountIdIn(accountIds)) {
                        String key = uploadLog.getAccountId() + "|" + uploadLog.getChangeType();
                        LocalDateTime current = lastSuccess.get(key);
                        if (current == null || (uploadLog.getUploadedOn() != null
                                        && uploadLog.getUploadedOn().isAfter(current))) {
                                lastSuccess.put(key, uploadLog.getUploadedOn());
                        }
                }
                if (lastSuccess.isEmpty()) {
                        return matched;
                }

                return matched.stream().filter(row -> {
                        LocalDateTime uploadedOn = lastSuccess
                                        .get(row.account().getAccountId() + "|" + row.changeType().name());
                        if (uploadedOn == null) {
                                return true;
                        }
                        if (row.changeType() != ChangeType.REPAYMENT) {
                                return false;
                        }
                        LocalDateTime modifiedOn = row.account().getModifiedOn();
                        return modifiedOn != null && modifiedOn.isAfter(uploadedOn);
                }).collect(Collectors.toList());
        }
        /** Данс бүрийн харилцагчийг нэгтгэж, нэг харилцагчид нэг л мөр (хамгийн сүүлд нээгдсэн данс) үлдээнэ. */
        private List<MatchedAccount> latestAccountPerClient(List<Account> accounts, ChangeType changeType) {
                Map<String, Account> latestByClient = new LinkedHashMap<>();
                for (Account account : accounts) {
                        latestByClient.merge(account.getClientId(), account, (current, candidate) -> Comparator
                                        .comparing(Account::getCreatedOn,
                                                        Comparator.nullsFirst(Comparator.<LocalDateTime>naturalOrder()))
                                        .compare(candidate, current) > 0 ? candidate : current);
                }
                return latestByClient.entrySet().stream()
                                .map(entry -> new MatchedAccount(changeType, entry.getKey(), entry.getValue()))
                                .collect(Collectors.toList());
        }

        private RecentCustomerData toSummary(MatchedAccount matched, Customer customer) {
                if (customer == null) {
                        log.warn("TBCUSTOMERS-д харилцагч олдсонгүй: clientId={}, данс={}", matched.clientId(),
                                        matched.account().getAccountId());
                }
                return RecentCustomerData.builder()
                                .changeType(matched.changeType().name())
                                .accountId(matched.account().getAccountId())
                                .clientId(matched.clientId())
                                .customerName(customer == null ? null : customer.getClientName())
                                .regnum(customer == null ? null : customer.getPinId())
                                .civilId(customer == null ? null : customer.getCivilId())
                                .phone(customer == null ? null
                                                : firstNotBlank(customer.getMobile(), customer.getPhone1()))
                                .balance(matched.account().getBalance())
                                .build();
        }

        private Map<String, Customer> loadCustomers(Iterable<String> clientIds) {
                return customerRepository.findAllById(clientIds).stream()
                                .collect(Collectors.toMap(Customer::getCustomerId, Function.identity(), (a, b) -> a));
        }

        private Map<String, List<LoanInstallment>> loadInstallments(Set<String> accountIds) {
                if (accountIds.isEmpty()) {
                        return Map.of();
                }
                return loanInstallmentRepository.findByAccountIdIn(List.copyOf(accountIds)).stream()
                                .collect(Collectors.groupingBy(LoanInstallment::getAccountId));
        }

        private Customer requireCustomer(String clientId, Map<String, Customer> customers) {
                Customer customer = customers.get(clientId);
                if (customer == null) {
                        throw new ResourceNotFoundException("Харилцагч олдсонгүй, id: " + clientId);
                }
                return customer;
        }

        private Optional<Account> latestAccount(List<Account> accounts) {
                return accounts.stream().max(Comparator.comparing(Account::getCreatedOn,
                                Comparator.nullsFirst(Comparator.naturalOrder())));
        }


        /** Зээлийн мэдээллийн блок. Иргэн болон хуулийн этгээдийн бүтцэд яг ижил тул хуваалцана. */
        private LoanInformation buildLoanInformation(Account loanAccount, List<LoanInstallment> installments) {
                boolean closed = loanAccount != null && ACCOUNT_STATUS_CLOSED.equals(loanAccount.getAccountStatus());
                LoanInformation loanInformation = loanAccount == null ? null
                                : LoanInformation.builder()
                                                .action("add")
                                                .contractDate(loanAccount.getOpenDate() == null ? null
                                                                : loanAccount.getOpenDate().toLocalDate())
                                                // Протокол 7.3: зээлийн дансны дугаар байхгүй бол давхардахгүй код үүсгэнэ гэсэн тул
                                                // TBACCOUNTS.ContractId хоосон үед дансны дугаарыг ашиглана.
                                                .contractNo(firstNotBlank(loanAccount.getContractId(),
                                                                loanAccount.getAccountId()))
                                                .amountLcy(zeroIfNull(loanAccount.getAppliedAmount()))
                                                .balanceLcy(zeroIfNull(loanAccount.getBalance()))
                                                .interestBalanceLcy(zeroIfNull(loanAccount.getInterestBalance()))
                                                .additionalInterestBalanceLcy(zeroIfNull(loanAccount.getPenaltyBalance()))
                                                // Зээлүүд төгрөгөөр олгогддог тул валютын дүнгүүд 0, ханш 1 байна.
                                                .amountFcy(BigDecimal.ZERO)
                                                .balanceFcy(BigDecimal.ZERO)
                                                .interestBalanceFcy(BigDecimal.ZERO)
                                                .additionalInterestBalanceFcy(BigDecimal.ZERO)
                                                .currencyRate(BigDecimal.ONE)
                                                .currency(loanCurrency)
                                                .loanProvenance(loanProvenance)
                                                .sector(loanSector)
                                                .loanClass(loanClass)
                                                .type(loanTypeCode)
                                                .interestRate(zeroIfNull(loanAccount.getInterestRate()))
                                                .additionalInterestRate(zeroIfNull(loanAccount.getExcessRate()))
                                                // TBACCOUNTS-д шимтгэл, хураамжийн багана байхгүй тул 0.
                                                .commission(BigDecimal.ZERO)
                                                .fee(BigDecimal.ZERO)
                                                .startedDate(loanAccount.getOpenDate())
                                                .expDate(loanAccount.getMatureDate() == null ? null
                                                                : loanAccount.getMatureDate().toLocalDate())
                                                .status(closed ? loanStatusClosed : loanStatusActive)
                                                // Протокол 7.24, 7.25: зээл хаах үед л бөглөнө.
                                                .decideStatus(closed ? loanDecideStatus : null)
                                                .paidDate(closed ? toLocalDate(loanAccount.getStatusDate() != null
                                                                ? loanAccount.getStatusDate()
                                                                : loanAccount.getModifiedOn()) : null)
                                                .loanTransactions(LoanTransactions.builder()
                                                                .loanScheduleType(loanScheduleType)
                                                                // Хуваарь өөрчлөгдсөн эсэхийг мэдэх боломжгүй тул 0.
                                                                .loanScheduleStatus("0")
                                                                .loanSchedule(sortByInstallmentId(installments).stream()
                                                                                .map(this::toLoanSchedule)
                                                                                .collect(Collectors.toList()))
                                                                .loanPayment(toLoanPayments(installments,
                                                                                loanAccount.getBalance()))
                                                                .build())
                                                .build();
                return loanInformation;
        }

        /**
         * Хуулийн этгээдийн ЗМС payload (/upload-entity). Зээлийн хэсэг нь иргэнийхтэй яг ижил тул
         * buildLoanInformation-ыг хуваалцана.
         *
         * Анхаар: o_state_regnum, o_numof_employee, o_company_type, o_orgrate, o_ceo-гийн дэлгэрэнгүй,
         * хувьцаа эзэмшигчид гэсэн протоколын заавал талбарууд TBCUSTOMERS-д багана байхгүй тул
         * тохиргоо/хоосон утгаар явна — ЗМС-д илгээхээс өмнө эдгээрийг эх сандаа нэмэх шаардлагатай.
         */
        private EntityData buildEntityData(Customer customer, Account loanAccount,
                        List<LoanInstallment> installments) {

                LoanInformation loanInformation = buildLoanInformation(loanAccount, installments);

                return EntityData.builder()
                                .action("add")
                                // TBCUSTOMERS-д улсын бүртгэлийн дугаарын багана байхгүй.
                                .stateRegnum(null)
                                .regnum(customer.getOrgPinId())
                                .customerName(customer.getClientName())
                                .isForeign(0)
                                .birthdate(customer.getBirthDate())
                                .address(buildEntityAddress(customer))
                                .phone(firstNotBlank(customer.getMobile(), customer.getPhone1()))
                                .email(customer.getEmail() == null || customer.getEmail().isBlank()
                                                ? "nomail@gmail.com"
                                                : customer.getEmail())
                                .numOfEmployee(orgNumOfEmployee)
                                .companyType(orgCompanyType)
                                .orgRate(OrgRate.builder()
                                                .agency(orgRateAgency)
                                                .rating(orgRateValue)
                                                .build())
                                // TBCUSTOMERS-д зөвхөн DIRECTORNAME байгаа тул бусад талбар хоосон.
                                .ceo(OrgCeo.builder()
                                                .firstname(customer.getDirectorName())
                                                .isForeign(0)
                                                .build())
                                .numOfShareholderOrg(0)
                                .numOfShareholderCustomer(0)
                                .bankRelation(CustomerBankRelation.builder()
                                                .action("add")
                                                .relation(customerBankRelation)
                                                .build())
                                .loanInformation(loanInformation == null ? null : List.of(loanInformation))
                                .build();
        }

        /** Хуулийн этгээдийн хаяг — иргэнийхтэй ижил эх өгөгдөл, зөвхөн байрны талбарын нэр өөр. */
        private EntityAddress buildEntityAddress(Customer customer) {
                CustomerAddress citizenAddress = buildAddress(customer);
                return EntityAddress.builder()
                                .addressFull(citizenAddress.getAddressFull())
                                .aimagCityName(citizenAddress.getAimagCityName())
                                .aimagCityCode(citizenAddress.getAimagCityCode())
                                .soumDistrictName(citizenAddress.getSoumDistrictName())
                                .soumDistrictCode(citizenAddress.getSoumDistrictCode())
                                .bagKhorooName(citizenAddress.getBagKhorooName())
                                .bagKhorooCode(citizenAddress.getBagKhorooCode())
                                .streetName(citizenAddress.getStreetName())
                                .regionName(citizenAddress.getRegionName())
                                .townName(citizenAddress.getTownName())
                                .apartmentName(citizenAddress.getApartmentName())
                                .zipcode(citizenAddress.getZipcode())
                                .build();
        }
        /**
         * ЗМС рүү илгээх нэг харилцагчийн payload.
         *
         * @param loanAccount  зээлийн мэдээлэлд ашиглах данс, байхгүй бол null.
         * @param installments уг зээлийн хуваарийн мөрүүд.
         * @param hasAccounts  харилцагчид ямар нэг данс байгаа эсэх (o_c_customer_bank_relation-д хэрэгтэй).
         */
        private CustomerData buildCustomerData(Customer customer, Account loanAccount,
                        List<LoanInstallment> installments, boolean hasAccounts) {

                LoanInformation loanInformation = buildLoanInformation(loanAccount, installments);

                return CustomerData.builder()
                                .action("add")
                                .civilId(customer.getCivilId())
                                .regnum(customer.getPinId())
                                .customerName(customer.getClientName())
                                .lastname(customer.getFirstName())
                                .familyname(customer.getFamilyName())
                                .isForeign(0)
                                .birthdate(customer.getBirthDate())
                                .address(buildAddress(customer))
                                .phone(firstNotBlank(customer.getMobile(), customer.getPhone1()))
                                .email(customer.getEmail() == null || customer.getEmail().isBlank()
                                                ? "nomail@gmail.com"
                                                : customer.getEmail())
                                .taxNumber(customer.getOrgPinId())
                                // TBCUSTOMERS-д гэр бүлийн гишүүдийн тоо талбар байхгүй тул одоогоор тогтмол.
                                .familyNumOfMembers(2)
                                // Ажил эрхлэлтийн мэдээлэл эх сангаас гардаггүй. 1 гэж илгээвэл протоколын
                                // дагуу c_job объект заавал болох тул (CDE1033) 0 гэж илгээнэ.
                                .isEmployed(0)
                                .loanInformation(loanInformation == null ? null : List.of(loanInformation))
                                .bankRelation(CustomerBankRelation.builder()
                                                .action("add")
                                                .relation(hasAccounts ? customerBankRelation : null)
                                                .build())
                                .build();
        }

        /**
         * Хаягийг TBCUSTOMERS-ийн задалсан багануудаас (AIMAGCITYNAME, ...CODE г.м) бүрдүүлнэ. Эдгээр нь
         * хоосон бол (TBCLIENTS-ээс миграцлагдсан хуучин мөрүүдэд) ADDRESS1-ийг regex-ээр задална.
         * Протоколын 2.2-2.8 талбарууд заавал тул кодууд бөглөгдөөгүй бол ЗМС VAE1004/VAE1008/VAE1012
         * алдаа буцаана — тэдгээрийг "Харилцагчийн бүртгэл" цонхоор нөхнө.
         */
        private CustomerAddress buildAddress(Customer customer) {
                CustomerAddress.CustomerAddressBuilder address = CustomerAddress.builder()
                                .addressFull(customer.getAddress1())
                                .aimagCityName(customer.getAimagCityName())
                                .aimagCityCode(customer.getAimagCityCode())
                                .soumDistrictName(customer.getSoumDistrictName())
                                .soumDistrictCode(customer.getSoumDistrictCode())
                                .bagKhorooName(customer.getBagKhorooName())
                                .bagKhorooCode(customer.getBagKhorooCode())
                                .streetName(customer.getStreetName())
                                .apartmentName(customer.getAddress2());

                boolean namesMissing = customer.getAimagCityName() == null || customer.getSoumDistrictName() == null
                                || customer.getBagKhorooName() == null || customer.getStreetName() == null;
                Matcher matcher = customer.getAddress1() == null ? null
                                : ADDRESS_PATTERN.matcher(customer.getAddress1().trim());
                if (namesMissing && matcher != null && matcher.matches()) {
                        address.aimagCityName(matcher.group("aimag"))
                                        .soumDistrictName(matcher.group("soum"))
                                        .bagKhorooName(matcher.group("bag"))
                                        .streetName(matcher.group("street"))
                                        .apartmentName(matcher.group("apartment"));
                }
                return address.build();
        }

        private static BigDecimal zeroIfNull(BigDecimal value) {
                return value == null ? BigDecimal.ZERO : value;
        }

        private static LocalDate toLocalDate(LocalDateTime value) {
                return value == null ? null : value.toLocalDate();
        }

        private static String firstNotBlank(String first, String second) {
                if (first != null && !first.isBlank()) {
                        return first;
                }
                return second;
        }

        private LoanSchedule toLoanSchedule(LoanInstallment loanInstallment) {
                return LoanSchedule.builder()
                                .action("add")
                                .dueDate(loanInstallment.getDueDate() == null ? null
                                                : loanInstallment.getDueDate().toLocalDate().toString())
                                .principal(loanInstallment.getPrincipal() == null ? null
                                                : loanInstallment.getPrincipal().toString())
                                .interest(loanInstallment.getInterest() == null ? null
                                                : loanInstallment.getInterest().toString())
                                .additional("0.00")
                                .balance(loanInstallment.getAfterBalance() == null ? null
                                                : loanInstallment.getAfterBalance().toString())
                                .build();
        }

        /** Хуваарийг төлөлтийн дарааллаар (INSTALLMENTID: 1 = эхний төлөлт) эрэмбэлнэ. */
        private List<LoanInstallment> sortByInstallmentId(List<LoanInstallment> installments) {
                return installments.stream()
                                .sorted(Comparator.comparing(LoanInstallment::getInstallmentId,
                                                Comparator.nullsFirst(Comparator.naturalOrder())))
                                .collect(Collectors.toList());
        }

        /**
         * Төлөгдсөн хуваарийн мөрүүд. Төлөлт хийгдэхэд TBACCOUNTS.balance буурдаг тул дансны одоогийн
         * үлдэгдэл нь хуваарийн мөрийн дараах үлдэгдлээс (afterBalance) бага буюу тэнцүү бол уг төлөлт
         * хийгдсэн гэж үзнэ. Бүрэн хаагдсан зээлд (balance = 0) бүх мөр төлөгдсөн гэж гарна.
         */
        private List<LoanPayment> toLoanPayments(List<LoanInstallment> installments, BigDecimal currentBalance) {
                if (currentBalance == null) {
                        return List.of();
                }
                return sortByInstallmentId(installments).stream()
                                .filter(installment -> installment.getAfterBalance() != null
                                                && currentBalance.compareTo(installment.getAfterBalance()) <= 0)
                                .map(this::toLoanPayment)
                                .collect(Collectors.toList());
        }

        private LoanPayment toLoanPayment(LoanInstallment loanInstallment) {
                return LoanPayment.builder()
                                .action("add")
                                // Протокол 10.2: төлөлт хийгдсэн огноо. Тусдаа багана байхгүй тул мөрийн сүүлийн
                                // өөрчлөлтийн хугацааг (MODIFIEDON) ашиглана.
                                .paymentDate(PAYMENT_DATE_FORMAT.format(loanInstallment.getModifiedOn() != null
                                                ? loanInstallment.getModifiedOn()
                                                : loanInstallment.getCreatedOn()))
                                .dueDate(loanInstallment.getDueDate() == null ? null
                                                : loanInstallment.getDueDate().toLocalDate().toString())
                                .principal(loanInstallment.getPrincipal() == null ? null
                                                : loanInstallment.getPrincipal().toString())
                                .interest(loanInstallment.getInterest() == null ? null
                                                : loanInstallment.getInterest().toString())
                                .additional("0.00")
                                .build();
        }

        /** Шүүлтэд таарсан данс, түүний харилцагч, ямар өөрчлөлтийн улмаас таарсан төрөл. */
        private record MatchedAccount(ChangeType changeType, String clientId, Account account) {
        }
}
