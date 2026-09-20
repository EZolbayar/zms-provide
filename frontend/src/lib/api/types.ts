// Types below mirror the backend DTOs in com.example.terguun.dto / dto.sain so the frontend
// stays a 1:1 fit with com.example.terguun.conttoller.ApiController.

/** com.example.terguun.dto.HttpResponse */
export type ApiResponse<T> = {
    status: number;
    data: T;
    errorCode?: string;
    errorMessage?: string;
    errorFields?: Record<string, string>;
};

/** com.example.terguun.dto.Login.Request */
export type LoginRequest = {
    userId: string;
    password: string;
};

/** com.example.terguun.dto.Login.Response */
export type LoginResponse = {
    userId: string;
    userName: string;
    timeLoggedIn: string;
    isAdmin: boolean;
};

/** com.example.terguun.dto.AccountDto (fields relevant to the admin UI) */
export type Account = {
    accountId: string;
    branchId?: string;
    clientId: string;
    productId: string;
    accountType: string;
    accountStatus: string;
    balance: number;
    interestRate?: number;
    term?: number;
    openDate: string;
    matureDate?: string;
    contractId?: string;
};

/** com.example.terguun.service.CustomerService CLIENT_TYPE_* утгууд (TBCLIENTS.ClientType bit-тэй ижил) */
export const CLIENT_TYPE_CITIZEN = "1"; // Монгол улсын иргэн
export const CLIENT_TYPE_LEGAL_ENTITY = "0"; // Хуулийн этгээд

/** com.example.terguun.dto.CustomerDto - TBCUSTOMERS хүснэгтийн бүртгэл */
export type Customer = {
    /** TBCLIENTS.ClientID-тэй ижил тэг-падтай тоон текст, ж: "00993" */
    customerId: string;
    branchId?: string;
    clientType?: string;
    clientName?: string;
    directorName?: string;
    familyName?: string;
    firstName?: string;
    pinId?: string;
    nationalId?: string;
    orgPinId?: string;
    birthDate?: string;
    address1?: string;
    address2?: string;
    phone1?: string;
    phone2?: string;
    mobile?: string;
    fax?: string;
    email?: string;
    isStaff?: boolean;
    isShareHolder?: boolean;
    reminder?: string;
    civilId?: string;
    aimagCityName?: string;
    aimagCityCode?: string;
    soumDistrictName?: string;
    soumDistrictCode?: string;
    bagKhorooName?: string;
    bagKhorooCode?: string;
    streetName?: string;
    createdOn?: string;
    createdBy?: string;
    modifiedOn?: string;
    modifiedBy?: string;
};

/** POST /api/customers-д илгээх бие. Сервер дээр customerId болон аудитын талбарууд бөглөгдөнө. */
export type CustomerRegistration = Omit<Customer, "customerId" | "createdOn" | "createdBy" | "modifiedOn" | "modifiedBy">;

/** com.example.terguun.dto.sain.CustomerAddress */
export type CustomerAddress = {
    o_c_address_full?: string;
    o_c_address_aimag_city_name?: string;
    o_c_address_aimag_city_code?: string;
    o_c_address_soum_district_name?: string;
    o_c_address_soum_district_code?: string;
    o_c_address_bag_khoroo_name?: string;
    o_c_address_bag_khoroo_code?: string;
    o_c_address_street_name?: string;
    o_c_address_apartment_name_number?: string;
};

/** com.example.terguun.dto.sain.CustomerBankRelation */
export type CustomerBankRelation = {
    action?: string;
    relation?: string;
};

/** com.example.terguun.dto.sain.LoanSchedule - талбарын нэрс @JsonProperty-той тэнцүү */
export type LoanSchedule = {
    action?: string;
    o_c_schedule_due_date?: string;
    o_c_schedule_principal?: string;
    o_c_schedule_interest?: string;
    o_c_schedule_additional?: string;
    o_c_schedule_balance?: string;
};

/** com.example.terguun.dto.sain.LoanPayment - төлөгдсөн хуваарийн мөрүүд */
export type LoanPayment = {
    action?: string;
    o_c_payment_date?: string;
    o_c_payment_due_date?: string;
    o_c_payment_principal?: string;
    o_c_payment_interest?: string;
    o_c_payment_additional?: string;
};

/** com.example.terguun.dto.sain.LoanTransactions */
export type LoanTransactions = {
    o_c_loan_schedule?: LoanSchedule[];
    o_c_loan_payment?: LoanPayment[];
};

/** com.example.terguun.dto.sain.LoanInformation (UI-д хэрэглэгддэг талбарууд) */
export type LoanInformation = {
    action?: string;
    o_c_loan_contractno?: string;
    o_c_loan_contract_date?: string;
    o_c_loan_amount_lcy?: number;
    o_c_loan_balance_lcy?: number;
    o_c_loan_interest_balance_lcy?: number;
    o_c_loan_starteddate?: string;
    o_c_loan_expdate?: string;
    o_c_loan_status?: string;
    o_c_loan_type?: string;
    o_c_loan_transactions?: LoanTransactions;
};

/** com.example.terguun.dto.sain.CustomerData, JSON field names kept snake_case to match @JsonProperty */
export type CustomerData = {
    action?: string;
    c_civil_id?: string;
    o_c_regnum?: string;
    o_c_customer_name?: string;
    c_lastname?: string;
    c_familyname?: string;
    o_c_isforeign?: number;
    o_c_birthdate?: string;
    o_c_address?: CustomerAddress;
    o_c_phone?: string;
    o_c_email?: string;
    c_tax_number?: string;
    c_isemployed?: number;
    o_c_customer_bank_relation?: CustomerBankRelation;
    /** Backend нь List<LoanInformation> болгон илгээдэг тул массив. */
    o_c_loan_information?: LoanInformation[];
};

/** com.example.terguun.dto.sain.CitizenUploadResponse */
export type UploadResult = {
    success: boolean;
    errors?: string[];
    action?: string;
};

/** com.example.terguun.dto.RecentCustomerData.ChangeType */
export type ChangeType = "NEW_LOAN" | "CLOSED_LOAN" | "REPAYMENT";

/**
 * com.example.terguun.dto.RecentCustomerData - жагсаалтын нэг мөр (хураангуй).
 * ЗМС рүү илгээх бүтэн payload нь энд ирэхгүй, илгээх үед сервер дээр угсрагдана.
 */
export type RecentCustomerChange = {
    changeType: ChangeType;
    accountId?: string;
    clientId?: string;
    customerName?: string;
    regnum?: string;
    civilId?: string;
    phone?: string;
    balance?: number;
};

/** com.example.terguun.dto.RecentUploadItem - илгээхээр сонгосон мөр */
export type RecentUploadItem = {
    clientId?: string;
    accountId?: string;
    changeType?: ChangeType;
};

/** Жагсаалтад харагдах өөрчлөлтийн төрлийн нэрс */
export const CHANGE_TYPE_LABELS: Record<ChangeType, string> = {
    NEW_LOAN: "Шинэ зээл",
    CLOSED_LOAN: "Зээл хаах",
    REPAYMENT: "Эргэн төлөлт",
};
