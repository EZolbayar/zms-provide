package com.example.terguun.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ЗМС рүү илгээсэн оролдлогын бүртгэл. Амжилттай мөрийг "Нийлүүлэх мэдээлэл" жагсаалтаас хасахад
 * ашиглана. Хүснэгтийг resources/schema.sql үүсгэнэ.
 */
@Entity
@Table(name = "TBSAINUPLOADLOG")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SainUploadLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOGID")
    private Long logId;

    @Column(name = "CLIENTID")
    private String clientId;

    @Column(name = "ACCOUNTID")
    private String accountId;

    /** NEW_LOAN / CLOSED_LOAN / REPAYMENT. Гараар татсан илгээлтэд хоосон. */
    @Column(name = "CHANGETYPE")
    private String changeType;

    /** TBCUSTOMERS.CLIENTTYPE: "1" иргэн, "0" хуулийн этгээд. */
    @Column(name = "CUSTOMERTYPE")
    private String customerType;

    /** Аль сувгаар илгээснийг тэмдэглэнэ: /upload-citizen эсвэл /upload-entity. */
    @Column(name = "ENDPOINT")
    private String endpoint;

    @Column(name = "PATCHNUMBER")
    private String patchNumber;

    @Column(name = "SUCCESS")
    private Boolean success;

    @Column(name = "ERRORMESSAGE")
    private String errorMessage;

    /** Илгээх үеийн TBACCOUNTS.MODIFIEDON. Эргэн төлөлт дахин гарах эсэхийг эндээс шийднэ. */
    @Column(name = "ACCOUNTMODIFIEDON")
    private LocalDateTime accountModifiedOn;

    @Column(name = "UPLOADEDON")
    private LocalDateTime uploadedOn;

    @Column(name = "UPLOADEDBY")
    private String uploadedBy;
}
