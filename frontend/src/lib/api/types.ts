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

/** com.example.terguun.dto.sain.CustomerAddress */
export type CustomerAddress = {
    addressFull?: string;
    aimagCityName?: string;
    soumDistrictName?: string;
    bagKhorooName?: string;
    streetName?: string;
    apartmentName?: string;
};

/** com.example.terguun.dto.sain.CustomerBankRelation */
export type CustomerBankRelation = {
    action?: string;
    relation?: string;
};

/** com.example.terguun.dto.sain.LoanSchedule */
export type LoanSchedule = {
    action?: string;
    dueDate?: string;
    principal?: string;
    interest?: string;
    balance?: string;
};

/** com.example.terguun.dto.sain.LoanInformation (subset used by the UI) */
export type LoanInformation = {
    action?: string;
    contractNo?: string;
    contractDate?: string;
    amountLcy?: number;
    balanceLcy?: number;
    interestBalanceLcy?: number;
    startedDate?: string;
    expDate?: string;
    status?: string;
    type?: string;
    loanTransactions?: { loanSchedule?: LoanSchedule[] };
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
    o_c_loan_information?: LoanInformation;
};

/** com.example.terguun.dto.sain.CitizenUploadResponse */
export type UploadResult = {
    success: boolean;
    errors?: string[];
    action?: string;
};
