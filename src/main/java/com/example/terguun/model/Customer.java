package com.example.terguun.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TBCLIENTS-тэй ижил бүтэцтэй, дээр нь регистрийн дугаар (CIVILID) болон задалсан хаягийн
 * баганууд нэмэгдсэн шинэ харилцагчийн хүснэгт. Хүснэгтийг resources/schema.sql үүсгэнэ.
 *
 * CUSTOMERID нь автоматаар үүсдэггүй (IDENTITY биш): TBCLIENTS.ClientID-тэй ижил тэг-падтай тоон
 * текст ("00001" г.м) бөгөөд CustomerService#generateNextCustomerId дугаарлалтыг гараар үргэлжлүүлнэ.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "TBCUSTOMERS")
public class Customer {

    @Id
    @Column(name = "CUSTOMERID")
    private String customerId;
    @Column(name = "BRANCHID")
    private String branchId;
    @Column(name = "CLIENTTYPE")
    private String clientType;
    @Column(name = "CLIENTNAME")
    private String clientName;
    @Column(name = "DIRECTORNAME")
    private String directorName;
    @Column(name = "FAMILYNAME")
    private String familyName;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "PINID")
    private String pinId;
    @Column(name = "NATIONALID")
    private String nationalId;
    @Column(name = "ORGPINID")
    private String orgPinId;
    @Column(name = "BIRTHDATE")
    private LocalDate birthDate;
    @Column(name = "ADDRESS1")
    private String address1;
    @Column(name = "ADDRESS2")
    private String address2;
    @Column(name = "PHONE1")
    private String phone1;
    @Column(name = "PHONE2")
    private String phone2;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ISSTAFF")
    private Boolean isStaff;
    @Column(name = "ISSHAREHOLDER")
    private Boolean isShareHolder;
    @Column(name = "REMINDER")
    private String reminder;

    // TBCLIENTS дээр байхгүй, шинээр нэмэгдсэн баганууд.
    @Column(name = "CIVILID")
    private String civilId;
    @Column(name = "AIMAGCITYNAME")
    private String aimagCityName;
    @Column(name = "AIMAGCITYCODE")
    private String aimagCityCode;
    @Column(name = "SOUMDISTRICTNAME")
    private String soumDistrictName;
    @Column(name = "SOUMDISTRICTCODE")
    private String soumDistrictCode;
    @Column(name = "BAGKHOROONAME")
    private String bagKhorooName;
    @Column(name = "BAGKHOROOCODE")
    private String bagKhorooCode;
    @Column(name = "STREETNAME")
    private String streetName;

    @Column(name = "CREATEDON")
    private LocalDateTime createdOn;
    @Column(name = "CREATEDBY")
    private String createdBy;
    @Column(name = "MODIFIEDON")
    private LocalDateTime modifiedOn;
    @Column(name = "MODIFIEDBY")
    private String modifiedBy;
}
