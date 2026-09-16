import { request, simulate } from "./client";
import { usingDummyData } from "./config";
import type { LoginResponse } from "./types";

const dummyUsers: Record<string, { userName: string; isAdmin: boolean }> = {
    admin: { userName: "Системийн администратор", isAdmin: true },
    operator: { userName: "Демо оператор", isAdmin: false },
};

/** POST /api/login - com.example.terguun.conttoller.ApiController#login */
export function login(userId: string, password: string): Promise<LoginResponse> {
    if (usingDummyData) {
        const demoUser = dummyUsers[userId.toLowerCase()];
        if (!demoUser || password.length === 0) {
            return Promise.reject(new Error("Нэвтрэх нэр эсвэл нууц үг буруу байна"));
        }
        return simulate({ userId, userName: demoUser.userName, timeLoggedIn: new Date().toISOString(), isAdmin: demoUser.isAdmin });
    }
    return request<LoginResponse>("/login", { method: "POST", body: JSON.stringify({ userId, password }) });
}

/** POST /api/logout/{userId} - com.example.terguun.conttoller.ApiController#logout */
export function logout(userId: string): Promise<void> {
    return usingDummyData ? simulate(undefined) : request<void>(`/logout/${encodeURIComponent(userId)}`, { method: "POST" });
}
