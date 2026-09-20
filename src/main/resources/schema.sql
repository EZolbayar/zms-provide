-- Hibernate-ийн ddl-auto=none хэвээр байгаа тул (одоо байгаа TBCLIENTS/TBUSERS зэрэг хүснэгтэд
-- хөндлөнгөөс өөрчлөлт оруулахгүйн тулд) шинэ TBCUSTOMERS хүснэгтийг энэ script үүсгэнэ.
-- spring.sql.init.mode=always тул апп асах бүрд ажиллана, доорх шалгалтууд давтан үүсгэхээс сэргийлнэ.
-- Анхаар: Spring-ийн script ажиллуулагч ";" тэмдгээр statement-үүдийг тусгаарладаг тул
-- BEGIN...END блок дотор ";" ашиглаж болохгүй.

-- CUSTOMERID нь TBCLIENTS.ClientID-тэй яг адилхан тэг-падтай тоон текст (ж: "00001") байна. IDENTITY
-- биш, тул миграцлагдсан мөрүүд ClientID-гээ шууд авч, шинээр бүртгэгдэх харилцагчид энэ дугаарлалтыг
-- үргэлжлүүлнэ (CustomerService#generateNextCustomerId харна уу).
IF OBJECT_ID(N'dbo.TBCUSTOMERS', N'U') IS NULL
BEGIN
CREATE TABLE dbo.TBCUSTOMERS (
    CUSTOMERID        NVARCHAR(15)   NOT NULL,
    BRANCHID          NVARCHAR(20)   NULL,
    CLIENTTYPE        NVARCHAR(10)   NULL,
    CLIENTNAME        NVARCHAR(200)  NULL,
    DIRECTORNAME      NVARCHAR(200)  NULL,
    FAMILYNAME        NVARCHAR(100)  NULL,
    FIRSTNAME         NVARCHAR(100)  NULL,
    PINID             NVARCHAR(20)   NULL,
    NATIONALID        NVARCHAR(20)   NULL,
    ORGPINID          NVARCHAR(20)   NULL,
    BIRTHDATE         DATE           NULL,
    ADDRESS1          NVARCHAR(400)  NULL,
    ADDRESS2          NVARCHAR(400)  NULL,
    PHONE1            NVARCHAR(30)   NULL,
    PHONE2            NVARCHAR(30)   NULL,
    MOBILE            NVARCHAR(30)   NULL,
    FAX               NVARCHAR(30)   NULL,
    EMAIL             NVARCHAR(150)  NULL,
    ISSTAFF           BIT            NULL,
    ISSHAREHOLDER     BIT            NULL,
    REMINDER          NVARCHAR(500)  NULL,
    CIVILID           NVARCHAR(20)   NULL,
    AIMAGCITYNAME     NVARCHAR(100)  NULL,
    AIMAGCITYCODE     NVARCHAR(20)   NULL,
    SOUMDISTRICTNAME  NVARCHAR(100)  NULL,
    SOUMDISTRICTCODE  NVARCHAR(20)   NULL,
    BAGKHOROONAME     NVARCHAR(100)  NULL,
    BAGKHOROOCODE     NVARCHAR(20)   NULL,
    STREETNAME        NVARCHAR(200)  NULL,
    CREATEDON         DATETIME2(3)   NULL,
    CREATEDBY         NVARCHAR(50)   NULL,
    MODIFIEDON        DATETIME2(3)   NULL,
    MODIFIEDBY        NVARCHAR(50)   NULL,
    CONSTRAINT PK_TBCUSTOMERS PRIMARY KEY CLUSTERED (CUSTOMERID)
)
END;

-- Регистрийн дугаар (CIVILID) давхардахаас сэргийлнэ. Хоосон утгыг индекс алгасна.
IF OBJECT_ID(N'dbo.TBCUSTOMERS', N'U') IS NOT NULL AND NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'UX_TBCUSTOMERS_CIVILID' AND object_id = OBJECT_ID(N'dbo.TBCUSTOMERS'))
CREATE UNIQUE INDEX UX_TBCUSTOMERS_CIVILID ON dbo.TBCUSTOMERS (CIVILID) WHERE CIVILID IS NOT NULL;

-- TBCLIENTS-ийн бүх мэдээллийг TBCUSTOMERS руу нэг удаа шилжүүлнэ. TBCUSTOMERS хоосон үед л ажиллах тул
-- апп дараагийн асалт бүрд давхардуулж оруулахгүй (гараар устгасны дараа л дахин ажиллана).
-- CUSTOMERID <- ClientID шууд (аль хэдийн 5 оронтой тэг-падтай тул хөрвүүлэлт хэрэггүй). CLIENTTYPE нь
-- TBCLIENTS.ClientType bit-тэй ижил утгатай: "1" = Монгол улсын иргэн, "0" = хуулийн этгээд.
-- PINID-ийн үсгийг том болгоно (регистрийн дугаарын эхний 2 үсэг том байх ёстой). CIVILID болон задалсан хаягийн баганууд
-- (AIMAGCITYNAME гэх мэт) TBCLIENTS-д байхгүй тул NULL хэвээр үлдэж, ADDRESS1 бүтэн хаягийг агуулна.
IF OBJECT_ID(N'dbo.TBCLIENTS', N'U') IS NOT NULL AND OBJECT_ID(N'dbo.TBCUSTOMERS', N'U') IS NOT NULL AND NOT EXISTS (SELECT 1 FROM dbo.TBCUSTOMERS)
INSERT INTO dbo.TBCUSTOMERS (
    CUSTOMERID, BRANCHID, CLIENTTYPE, CLIENTNAME, DIRECTORNAME, FAMILYNAME, FIRSTNAME, PINID, NATIONALID, ORGPINID,
    BIRTHDATE, ADDRESS1, ADDRESS2, PHONE1, PHONE2, MOBILE, FAX, EMAIL, ISSTAFF, ISSHAREHOLDER, REMINDER,
    CREATEDON, CREATEDBY, MODIFIEDON, MODIFIEDBY
)
SELECT
    RTRIM(ClientID),
    NULLIF(RTRIM(BranchID), ''),
    CASE WHEN ClientType = 1 THEN N'1' ELSE N'0' END,
    NULLIF(RTRIM(ClientName), ''),
    NULLIF(RTRIM(DirectorName), ''),
    NULLIF(RTRIM(FamilyName), ''),
    NULLIF(RTRIM(FirstName), ''),
    UPPER(NULLIF(RTRIM(PinID), '')),
    NULLIF(RTRIM(NationalID), ''),
    NULLIF(RTRIM(OrgPinID), ''),
    CAST(BirthDate AS DATE),
    NULLIF(RTRIM(Address1), ''),
    NULLIF(RTRIM(Address2), ''),
    NULLIF(RTRIM(Phone1), ''),
    NULLIF(RTRIM(Phone2), ''),
    NULLIF(RTRIM(Mobile), ''),
    NULLIF(RTRIM(Fax), ''),
    NULLIF(RTRIM(Email), ''),
    IsStaff,
    IsShareHolder,
    NULLIF(RTRIM(Reminder), ''),
    CreatedOn,
    NULLIF(RTRIM(CreatedBy), ''),
    ModifiedOn,
    NULLIF(RTRIM(ModifiedBy), '')
FROM dbo.TBCLIENTS;

-- ЗМС рүү илгээсэн бүртгэл. Амжилттай илгээгдсэн мөр нь "Нийлүүлэх мэдээлэл" жагсаалтад дахин
-- гарч ирэхгүй (RecentlyDataService.buildForRecentChanges шүүнэ).
IF OBJECT_ID(N'dbo.TBSAINUPLOADLOG', N'U') IS NULL
BEGIN
CREATE TABLE dbo.TBSAINUPLOADLOG (
    LOGID              BIGINT IDENTITY(1,1) NOT NULL,
    CLIENTID           NVARCHAR(15)   NULL,
    ACCOUNTID          NVARCHAR(20)   NULL,
    CHANGETYPE         NVARCHAR(20)   NULL,
    CUSTOMERTYPE       NVARCHAR(10)   NULL,
    ENDPOINT           NVARCHAR(50)   NULL,
    PATCHNUMBER        NVARCHAR(50)   NULL,
    SUCCESS            BIT            NOT NULL,
    ERRORMESSAGE       NVARCHAR(2000) NULL,
    ACCOUNTMODIFIEDON  DATETIME2(3)   NULL,
    UPLOADEDON         DATETIME2(3)   NOT NULL,
    UPLOADEDBY         NVARCHAR(50)   NULL,
    CONSTRAINT PK_TBSAINUPLOADLOG PRIMARY KEY CLUSTERED (LOGID)
)
END;

-- Жагсаалтаас хасах шүүлт нь (ACCOUNTID, CHANGETYPE, SUCCESS)-аар хайдаг тул индекс нэмнэ.
IF OBJECT_ID(N'dbo.TBSAINUPLOADLOG', N'U') IS NOT NULL AND NOT EXISTS (SELECT 1 FROM sys.indexes WHERE name = N'IX_TBSAINUPLOADLOG_ACCOUNT' AND object_id = OBJECT_ID(N'dbo.TBSAINUPLOADLOG'))
CREATE INDEX IX_TBSAINUPLOADLOG_ACCOUNT ON dbo.TBSAINUPLOADLOG (ACCOUNTID, CHANGETYPE, SUCCESS);
