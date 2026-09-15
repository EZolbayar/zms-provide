export type ApiResponse<T> = {
    status: number;
    data: T;
    errorCode?: string;
    errorMessage?: string;
};

export type LoginResponse = {
    userId: string;
    userName: string;
    timeLoggedIn: string;
    isAdmin: boolean;
};

export type Account = {
    accountId: string;
    clientId: string;
    productId: string;
    accountType: string;
    accountStatus: string;
    balance: number;
    openDate: string;
};

export type CustomerData = Record<string, unknown> & {
    customerName?: string;
    regnum?: string;
    action?: string;
};

export type UploadResult = {
    success: boolean;
    errors?: string[];
    action?: string;
};

const baseUrl = process.env.NEXT_PUBLIC_API_BASE_URL ?? "http://localhost:8081";
export const usingDummyData = process.env.NEXT_PUBLIC_USE_DUMMY_DATA !== "false";

const dummyAccounts: Account[] = [
    { accountId: "LN-2026-00481", clientId: "C-100238", productId: "TERM-LOAN", accountType: "Loan", accountStatus: "ACTIVE", balance: 28450000, openDate: "2025-11-18" },
    { accountId: "LN-2026-00482", clientId: "C-100411", productId: "MICRO-LOAN", accountType: "Loan", accountStatus: "ACTIVE", balance: 8750000, openDate: "2026-02-04" },
    { accountId: "DP-2026-01923", clientId: "C-100238", productId: "SAVINGS", accountType: "Deposit", accountStatus: "ACTIVE", balance: 12600000, openDate: "2024-08-22" },
];

const dummyCustomer: CustomerData = {
    action: "add",
    civilId: "",
    regnum: "УБ90051234",
    customerName: "БАТ-ЭРДЭНЭ",
    lastname: "ГАНБААТАР",
    familyname: "БОРЖИГИН",
    isForeign: 0,
    birthdate: "1990-05-12",
    phone: "99112233",
    email: "bat-erdene@example.mn",
    address: { fullAddress: "Улаанбаатар, Сүхбаатар дүүрэг, 1-р хороо" },
    bankRelation: { action: "add", relation: "01" },
    loanInformation: { action: "add", contractNumber: "LN-2026-00481", contractDate: "2026-02-04" },
};

function simulate<T>(value: T): Promise<T> {
    return new Promise((resolve) => window.setTimeout(() => resolve(value), 450));
}

async function request<T>(path: string, init?: RequestInit): Promise<T> {
    let response: Response;
    try {
        response = await fetch(`${baseUrl}/api${path}`, {
            headers: { "Content-Type": "application/json", ...init?.headers },
            ...init,
        });
    } catch {
        throw new Error(`${baseUrl} хаягт холбогдож чадсангүй. NEXT_PUBLIC_API_BASE_URL болон API серверээ шалгана уу.`);
    }

    const body = (await response.json().catch(() => null)) as ApiResponse<T> | null;
    if (!response.ok || !body || body.status !== 200) {
        throw new Error(body?.errorMessage ?? `Хүсэлт амжилтгүй боллоо. Төлөв: ${response.status}.`);
    }
    return body.data;
}

export const api = {
    login: (userId: string, password: string) =>
        usingDummyData
            ? simulate({ userId, userName: userId === "admin" ? "Системийн администратор" : "Демо оператор", timeLoggedIn: new Date().toISOString(), isAdmin: userId === "admin" })
            : request<LoginResponse>("/login", { method: "POST", body: JSON.stringify({ userId, password }) }),
    logout: (userId: string) => usingDummyData ? simulate(undefined) : request<void>(`/logout/${encodeURIComponent(userId)}`, { method: "POST" }),
    accounts: () => usingDummyData ? simulate(dummyAccounts) : request<Account[]>("/accounts"),
    latestCustomer: () => usingDummyData ? simulate(dummyCustomer) : request<CustomerData | null>("/send-data"),
    customersForClients: (clientIds: string[]) =>
        usingDummyData
            ? simulate(clientIds.map((clientId) => ({ ...dummyCustomer, regnum: clientId })))
            : request<CustomerData[]>("/send-data", { method: "POST", body: JSON.stringify(clientIds) }),
    uploadCitizen: (data: CustomerData[]) =>
        usingDummyData
            ? simulate(data.map((item) => ({ success: true, action: String(item.action ?? "add") })))
            : request<UploadResult[]>("/upload-citizen", { method: "POST", body: JSON.stringify({ data }) }),
};