package com.example.zms_upload.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbUsers", schema = "dbo")
@IdClass(UserId.class)
@Getter
@Setter
public class User {

    @Id
    @Column(name = "BranchID")
    private String branchId;

    @Id
    @Column(name = "UserID")
    private String userId;

    @Column(name = "UserName")
    private String userName;

    // Sensitive credential, never expose over the API.
    @JsonIgnore
    @Column(name = "UserCode")
    private String userCode;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Mobile")
    private String mobile;

    @Column(name = "Email")
    private String email;

    @Column(name = "SerialPrefix")
    private String serialPrefix;

    @Column(name = "IsLoggedIn")
    private Boolean isLoggedIn;

    @Column(name = "TimeLoggedIn")
    private LocalDateTime timeLoggedIn;

    @Column(name = "TimeLoggedOut")
    private LocalDateTime timeLoggedOut;

    @Column(name = "ComputerName")
    private String computerName;

    @Column(name = "NetworkUserID")
    private String networkUserId;

    @Column(name = "IsCashier")
    private Boolean isCashier;

    @Column(name = "IsAdmin")
    private Boolean isAdmin;

    @Column(name = "IsDisabled")
    private Boolean isDisabled;

    @Column(name = "IsReport")
    private Boolean isReport;

    @Column(name = "CanChangePassword")
    private Boolean canChangePassword;

    @Column(name = "ChangedOn")
    private LocalDateTime changedOn;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
}
