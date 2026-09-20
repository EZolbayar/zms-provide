package com.example.terguun.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Нийлүүлэх мэдээллийн жагсаалтын нэг мөр. Дэлгэцэд харуулах хураангуй мэдээлэл л агуулна — ЗМС рүү
 * илгээх бүтэн payload нь мөр бүрд хэдэн КБ болдог тул илгээх үед л угсрагдана
 * (POST /api/send-data/upload).
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RecentCustomerData {

    /** ChangeType-ийн нэр: NEW_LOAN, CLOSED_LOAN, REPAYMENT. */
    private String changeType;

    /** Тухайн өөрчлөлтийг үүсгэсэн данс. changeType-тэй хослуулахад мөрийн давтагдашгүй түлхүүр болно. */
    private String accountId;

    private String clientId;

    private String customerName;

    /** Регистрийн дугаар (TBCUSTOMERS.PINID). */
    private String regnum;

    /** Иргэний бүртгэлийн дугаар (TBCUSTOMERS.CIVILID). Хоосон бол ЗМС CDE1002 алдаа буцаана. */
    private String civilId;

    private String phone;

    /** Зээлийн үлдэгдэл (TBACCOUNTS.BALANCE). */
    private BigDecimal balance;

    /** Нийлүүлэх мэдээллийн жагсаалтад харагдах өөрчлөлтийн төрлүүд. */
    public enum ChangeType {
        /** TBACCOUNTS.createdOn-оор сүүлийн хугацаанд нээгдсэн шинэ зээл. */
        NEW_LOAN,
        /** modifiedOn сүүлийн хугацаанд, accountStatus = "C", balance = 0 буюу хаагдсан зээл. */
        CLOSED_LOAN,
        /** modifiedOn нь createdOn-оос хойш өөрчлөгдсөн, идэвхтэй бөгөөд үлдэгдэлтэй буюу эргэн төлөлт. */
        REPAYMENT
    }
}
