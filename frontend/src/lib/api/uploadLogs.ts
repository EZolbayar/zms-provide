import { request, simulate } from "./client";
import { usingDummyData } from "./config";
import type { UploadLog } from "./types";

const dummyLogs: UploadLog[] = [
    {
        logId: 2,
        uploadedOn: "2026-09-21T10:15:00",
        uploadedBy: "ADMIN",
        clientId: "00150",
        customerName: "Энхээхүү Цолмон",
        accountId: "47000011",
        changeType: "REPAYMENT",
        customerType: "1",
        endpoint: "/upload-citizen",
        success: false,
        errorMessage: "RTE1017: customer_data[0].o_c_loan_information[0].o_c_loan_sector: LIE1049",
    },
    {
        logId: 1,
        uploadedOn: "2026-09-20T16:40:00",
        uploadedBy: "ADMIN",
        clientId: "00069",
        customerName: "Бат Болд",
        accountId: "42000003",
        changeType: "NEW_LOAN",
        customerType: "1",
        endpoint: "/upload-citizen",
        patchNumber: "202609201640000001",
        success: true,
    },
];

/** GET /api/upload-logs?from=YYYY-MM-DD&to=YYYY-MM-DD - ApiController#getUploadLogs */
export function getUploadLogs(from?: string, to?: string): Promise<UploadLog[]> {
    if (usingDummyData) {
        return simulate(dummyLogs);
    }
    const params = new URLSearchParams();
    if (from) params.set("from", from);
    if (to) params.set("to", to);
    const query = params.toString();
    return request<UploadLog[]>(`/upload-logs${query ? `?${query}` : ""}`);
}
