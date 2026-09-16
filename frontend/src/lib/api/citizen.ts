import { request, simulate } from "./client";
import { usingDummyData } from "./config";
import type { CustomerData, UploadResult } from "./types";

const dummyCustomer: CustomerData = {
    action: "add",
    c_civil_id: "УБ90051234ХХ",
    o_c_regnum: "C-100238",
    o_c_customer_name: "БАТ-ЭРДЭНЭ",
    c_lastname: "БАТ-ЭРДЭНЭ",
    c_familyname: "ГАНБААТАР",
    o_c_isforeign: 0,
    o_c_birthdate: "1990-05-12",
    o_c_phone: "99112233",
    o_c_email: "bat-erdene@example.mn",
    o_c_address: { addressFull: "Улаанбаатар, Сүхбаатар дүүрэг, 1-р хороо" },
    o_c_customer_bank_relation: { action: "add", relation: "01" },
    o_c_loan_information: {
        action: "add",
        contractNo: "LN-2026-00481",
        contractDate: "2026-02-04",
        amountLcy: 28450000,
        balanceLcy: 28450000,
        status: "ACTIVE",
        type: "Loan",
    },
};

/** GET /api/send-data - com.example.terguun.conttoller.ApiController#getCitizenUpload */
export function getLatestCustomer(): Promise<CustomerData | null> {
    return usingDummyData ? simulate(dummyCustomer) : request<CustomerData | null>("/send-data");
}

/** POST /api/send-data - com.example.terguun.conttoller.ApiController#getCitizenUploadForClients */
export function getCustomersForClients(clientIds: string[]): Promise<CustomerData[]> {
    return usingDummyData
        ? simulate(clientIds.map((clientId) => ({ ...dummyCustomer, o_c_regnum: clientId })))
        : request<CustomerData[]>("/send-data", { method: "POST", body: JSON.stringify(clientIds) });
}

/** POST /api/upload-citizen - com.example.terguun.conttoller.ApiController#uploadCitizen */
export function uploadCitizen(data: CustomerData[]): Promise<UploadResult[]> {
    return usingDummyData
        ? simulate(data.map((item) => ({ success: true, action: String(item.action ?? "add") })))
        : request<UploadResult[]>("/upload-citizen", { method: "POST", body: JSON.stringify({ data }) });
}
