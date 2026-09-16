import { request, simulate } from "./client";
import { usingDummyData } from "./config";
import type { Account } from "./types";

const dummyAccounts: Account[] = [
    { accountId: "LN-2026-00481", clientId: "C-100238", productId: "TERM-LOAN", accountType: "Loan", accountStatus: "ACTIVE", balance: 28450000, interestRate: 1.8, term: 24, openDate: "2025-11-18" },
    { accountId: "LN-2026-00482", clientId: "C-100411", productId: "MICRO-LOAN", accountType: "Loan", accountStatus: "ACTIVE", balance: 8750000, interestRate: 2.4, term: 12, openDate: "2026-02-04" },
    { accountId: "DP-2026-01923", clientId: "C-100238", productId: "SAVINGS", accountType: "Deposit", accountStatus: "ACTIVE", balance: 12600000, openDate: "2024-08-22" },
    { accountId: "LN-2025-00397", clientId: "C-100587", productId: "TERM-LOAN", accountType: "Loan", accountStatus: "CLOSED", balance: 0, interestRate: 1.6, term: 18, openDate: "2024-05-02" },
    { accountId: "DP-2026-02011", clientId: "C-100742", productId: "SAVINGS", accountType: "Deposit", accountStatus: "ACTIVE", balance: 3450000, openDate: "2026-04-01" },
];

/** GET /api/accounts - com.example.terguun.conttoller.ApiController#getAll */
export function getAccounts(): Promise<Account[]> {
    return usingDummyData ? simulate(dummyAccounts) : request<Account[]>("/accounts");
}
