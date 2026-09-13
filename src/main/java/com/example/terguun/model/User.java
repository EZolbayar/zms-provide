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

@Entity
@Table(name = "TBUSERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERID")
    private String userId;
    @Column(name = "BRANCHID")
    private String branchId;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "USERCODE")
    private String userCode;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "SERIALPREFIX")
    private String serialPrefix;
    @Column(name = "ISLOGGEDIN")
    private Boolean isLoggedIn;
    @Column(name = "TIMELOGGEDIN")
    private LocalDateTime timeLoggedIn;
    @Column(name = "TIMELOGGEDOUT")
    private LocalDateTime timeLoggedOut;
    @Column(name = "COMPUTERNAME")
    private String computerName;
    @Column(name = "NETWORKUSERID")
    private String networkUserId;
    @Column(name = "ISCASHIER")
    private Boolean isCashier;
    @Column(name = "ISADMIN")
    private Boolean isAdmin;
    @Column(name = "ISDISABLED")
    private Boolean isDisabled;
    @Column(name = "ISREPORT")
    private Boolean isReport;
    @Column(name = "CANCHANGEPASSWORD")
    private Boolean canChangePassword;
    @Column(name = "CHANGEDON")
    private LocalDateTime changedOn;
    @Column(name = "CREATEDON")
    private LocalDateTime createdOn;
    @Column(name = "CREATEDBY")
    private String createdBy;
    @Column(name = "MODIFIEDON")
    private LocalDateTime modifiedOn;
    @Column(name = "MODIFIEDBY")
    private String modifiedBy;
}