package com.example.terguun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Нийлүүлэх мэдээллийн жагсаалтаас илгээхээр сонгосон нэг мөр. */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RecentUploadItem {

    private String clientId;

    /** Тухайн өөрчлөлтийг үүсгэсэн данс. Зээлийн мэдээллийг яг үүнээс угсарна. */
    private String accountId;

    /** NEW_LOAN / CLOSED_LOAN / REPAYMENT — бүртгэлд хадгалж, дахин жагсаалтад гарахаас хасахад хэрэглэнэ. */
    private String changeType;
}
