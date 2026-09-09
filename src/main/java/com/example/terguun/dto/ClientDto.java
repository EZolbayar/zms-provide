package com.example.terguun.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Long clientId;
    private Long branchId;
    private String clientType;
    private String clientName;
    private String directorName;
    private String familyName;
    private String firstName;
    private String pinId;
    private String nationalId;
    private String orgPinId;
    private LocalDate birthDate;
    private String address1;
    private String address2;
    private String phone1;
    private String phone2;
    private String mobile;
    private String fax;
    private String email;
    private Boolean isStaff;
    private Boolean isShareHolder;
    private String reminder;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
