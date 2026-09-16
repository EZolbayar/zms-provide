"use client";

import { createContext, useCallback, useContext, useEffect, useMemo, useState } from "react";
import { api } from "@/lib/api";
import { clearSession, readSession, writeSession, type Session } from "@/lib/auth/session";

type AuthContextValue = {
    user: Session | null;
    isLoading: boolean;
    signIn: (userId: string, password: string) => Promise<void>;
    signOut: () => Promise<void>;
};

const AuthContext = createContext<AuthContextValue | null>(null);

export function AuthProvider({ children }: { children: React.ReactNode }) {
    const [user, setUser] = useState<Session | null>(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        setUser(readSession());
        setIsLoading(false);
    }, []);

    const signIn = useCallback(async (userId: string, password: string) => {
        const session = await api.login(userId, password);
        writeSession(session);
        setUser(session);
    }, []);

    const signOut = useCallback(async () => {
        if (user) {
            await api.logout(user.userId).catch(() => undefined);
        }
        clearSession();
        setUser(null);
    }, [user]);

    const value = useMemo(() => ({ user, isLoading, signIn, signOut }), [user, isLoading, signIn, signOut]);

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
    const context = useContext(AuthContext);
    if (!context) throw new Error("useAuth must be used within an AuthProvider");
    return context;
}
