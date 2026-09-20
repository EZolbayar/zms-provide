package com.example.terguun.dto;

import java.time.LocalDateTime;

import com.example.terguun.dto.sain.CustomerData;
import com.example.terguun.dto.sain.EntityData;

/**
 * Нэг мөрийн ЗМС payload. TBCUSTOMERS.CLIENTTYPE-аас хамаарч иргэн (/upload-citizen) эсвэл хуулийн
 * этгээд (/upload-entity) аль нэг нь бөглөгдөнө. Хамт яваа таних талбарууд нь илгээсний дараа
 * TBSAINUPLOADLOG-д бүртгэл бичихэд хэрэглэгдэнэ.
 */
public record SainUploadPayload(
        CustomerData citizen,
        EntityData entity,
        String clientId,
        String accountId,
        String changeType,
        String customerType,
        LocalDateTime accountModifiedOn) {

    /** Гараар татаж илгээх урсгалд (POST /api/upload-citizen) таних мэдээлэл байхгүй. */
    public static SainUploadPayload ofCitizen(CustomerData citizen) {
        return new SainUploadPayload(citizen, null, null, null, null, null, null);
    }

    public boolean isEntity() {
        return entity != null;
    }
}
