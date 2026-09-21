package com.example.terguun.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** "Илгээлтийн лог" хуудасны нэг мөр: TBSAINUPLOADLOG + харилцагчийн нэр. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadLogDto {
    private Long logId;
    private LocalDateTime uploadedOn;
    private String uploadedBy;
    private String clientId;
    private String customerName;
    private String accountId;
    private String changeType;
    private String customerType;
    private String endpoint;
    private String patchNumber;
    private Boolean success;
    private String errorMessage;
}
