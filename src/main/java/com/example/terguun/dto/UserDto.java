package com.example.terguun.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private Long branchId;
    private String userName;
    private String userCode;
    private String address;
    private String phone;
    private String mobile;
    private String email;
    private String serialPrefix;
    private Boolean isLoggedIn;
    private LocalDateTime timeLoggedIn;
    private LocalDateTime timeLoggedOut;
    private String computerName;
    private String networkUserId;
    private Boolean isCashier;
    private Boolean isAdmin;
    private Boolean isDisabled;
    private Boolean isReport;
    private Boolean canChangePassword;
    private LocalDateTime changedOn;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime modifiedOn;
    private String modifiedBy;
}
