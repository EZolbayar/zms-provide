package com.example.terguun.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Хаягийн лавлах (аймаг/хот → сум/дүүрэг). Хүснэгтийг resources/schema.sql үүсгэж бөглөнө. */
@Entity
@Table(name = "ADDRESS_MAPPING")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressMapping {

    @Column(name = "CITY_CODE")
    private String cityCode;

    @Column(name = "CITY_CODE_XYP")
    private String cityCodeXyp;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Id
    @Column(name = "DISTRICT_CODE")
    private String districtCode;

    @Column(name = "DISTRICT_CODE_XYP")
    private String districtCodeXyp;

    @Column(name = "DISTRICT_NAME")
    private String districtName;

    /** Баг/хорооны 3 баганад одоогоор өгөгдөл байхгүй. */
    @Column(name = "BAGKHOROO_CODE")
    private String bagkhorooCode;

    @Column(name = "BAGKHOROO_CODE_XYP")
    private String bagkhorooCodeXyp;

    @Column(name = "BAGKHOROO_NAME")
    private String bagkhorooName;
}
