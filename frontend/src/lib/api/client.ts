import { API_BASE_URL } from "./config";
import type { ApiResponse } from "./types";

export class ApiError extends Error {
    constructor(message: string, public status?: number, public errorCode?: string) {
        super(message);
        this.name = "ApiError";
    }
}

/** Calls `${API_BASE_URL}/api${path}` and unwraps the backend's HttpResponse<T> envelope. */
export async function request<T>(path: string, init?: RequestInit): Promise<T> {
    let response: Response;
    try {
        response = await fetch(`${API_BASE_URL}/api${path}`, {
            ...init,
            // init-ийн дараа байрлуулснаар дуудагчийн header-үүд Content-Type-г дарж бичихгүй.
            headers: { "Content-Type": "application/json", ...init?.headers },
        });
    } catch {
        throw new ApiError(`${API_BASE_URL} хаягт холбогдож чадсангүй. NEXT_PUBLIC_API_BASE_URL болон API серверээ шалгана уу.`);
    }

    const body = (await response.json().catch(() => null)) as ApiResponse<T> | null;
    if (!response.ok || !body || body.status !== 200) {
        throw new ApiError(body?.errorMessage ?? `Хүсэлт амжилтгүй боллоо. Төлөв: ${response.status}.`, body?.status ?? response.status, body?.errorCode);
    }
    return body.data;
}

/** Resolves to `value` after a short delay, used by the dummy-data mode to simulate network latency. */
export function simulate<T>(value: T, delayMs = 450): Promise<T> {
    return new Promise((resolve) => setTimeout(() => resolve(value), delayMs));
}
