import type { LoginResponse } from "@/lib/api";

const STORAGE_KEY = "zms.session";
const COOKIE_NAME = "zms_session";

export type Session = LoginResponse;

function isBrowser() {
    return typeof window !== "undefined";
}

export function readSession(): Session | null {
    if (!isBrowser()) return null;
    const raw = window.localStorage.getItem(STORAGE_KEY);
    if (!raw) return null;
    try {
        return JSON.parse(raw) as Session;
    } catch {
        return null;
    }
}

// A lightweight, non-HttpOnly cookie mirrors the session so middleware can gate admin routes on the edge.
export function writeSession(session: Session) {
    if (!isBrowser()) return;
    window.localStorage.setItem(STORAGE_KEY, JSON.stringify(session));
    document.cookie = `${COOKIE_NAME}=${session.userId}; path=/; max-age=${60 * 60 * 12}; SameSite=Lax`;
}

export function clearSession() {
    if (!isBrowser()) return;
    window.localStorage.removeItem(STORAGE_KEY);
    document.cookie = `${COOKIE_NAME}=; path=/; max-age=0; SameSite=Lax`;
}

export { COOKIE_NAME };
