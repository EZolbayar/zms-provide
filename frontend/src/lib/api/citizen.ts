import { request, simulate } from "./client";
import { usingDummyData } from "./config";
import type { CustomerData, RecentCustomerChange, RecentUploadItem, UploadResult } from "./types";

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
    o_c_address: { o_c_address_full: "Улаанбаатар, Сүхбаатар дүүрэг, 1-р хороо" },
    o_c_customer_bank_relation: { action: "add", relation: "01" },
    o_c_loan_information: [
        {
            action: "add",
            o_c_loan_contractno: "LN-2026-00481",
            o_c_loan_contract_date: "2026-02-04",
            o_c_loan_amount_lcy: 28450000,
            o_c_loan_balance_lcy: 28450000,
            o_c_loan_status: "ACTIVE",
            o_c_loan_type: "Loan",
        },
    ],
};

/** GET /api/send-data - шинэ зээл, хаагдсан зээл, эргэн төлөлтийг нэг дуудалтаар. ApiController#getRecentChanges */
export function getRecentChanges(): Promise<RecentCustomerChange[]> {
    if (usingDummyData) {
        return simulate([
            { changeType: "NEW_LOAN", accountId: "47000011", clientId: "00150", customerName: "БАТ-ЭРДЭНЭ", regnum: "УБ90051234", civilId: "888954521912", phone: "99112233", balance: 28450000 },
            { changeType: "CLOSED_LOAN", accountId: "46000232", clientId: "00151", customerName: "СОЁЛМАА", regnum: "УБ91103054", phone: "99223344", balance: 0 },
            { changeType: "REPAYMENT", accountId: "46000219", clientId: "00152", customerName: "ЭНХЖАРГАЛ", regnum: "УБ95061006", phone: "99334455", balance: 2662982 },
        ]);
    }
    return request<RecentCustomerChange[]>("/send-data");
}

/** POST /api/send-data/upload - сонгосон мөрүүдийг ЗМС рүү илгээнэ. ApiController#uploadRecentChanges */
export function uploadRecentChanges(items: RecentUploadItem[], userId?: string): Promise<UploadResult[]> {
    if (usingDummyData) {
        return simulate(items.map(() => ({ success: true, action: "add" })));
    }
    return request<UploadResult[]>("/send-data/upload", {
        method: "POST",
        headers: userId ? { "X-User-Id": userId } : undefined,
        body: JSON.stringify(items),
    });
}
/** POST /api/send-data - com.example.terguun.conttoller.ApiController#getCitizenUploadForClients */
export function getCustomersForClients(clientIds: string[]): Promise<CustomerData[]> {
    return usingDummyData
        ? simulate(clientIds.map((clientId) => ({ ...dummyCustomer, o_c_regnum: clientId })))
        : request<CustomerData[]>("/send-data", { method: "POST", body: JSON.stringify(clientIds) });
}

/** POST /api/upload-sain - com.example.terguun.conttoller.ApiController#uploadCitizen */
export function uploadCitizen(data: CustomerData[]): Promise<UploadResult[]> {
    console.log("Uploading citizen data:", data);
    return usingDummyData
        ? simulate(data.map((item) => ({ success: true, action: String(item.action ?? "add") })))
        : request<UploadResult[]>("/upload-sain", { method: "POST", body: JSON.stringify({ data }) });
}
