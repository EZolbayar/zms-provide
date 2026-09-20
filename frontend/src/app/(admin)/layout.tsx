"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { useAuth } from "@/contexts/AuthContext";
import { clearSession } from "@/lib/auth/session";
import { Sidebar } from "@/components/layout/Sidebar";

export default function AdminLayout({ children }: { children: React.ReactNode }) {
    const { user, isLoading } = useAuth();
    const router = useRouter();

    useEffect(() => {
        if (!isLoading && !user) {
            // localStorage-д session алга атлаа zms_session cookie үлдсэн бол middleware /login-оос
            // буцаан эргүүлж, дэлгэц эцэслэшгүй "Ачааллаж байна..." дээр гацдаг. Cookie-г цэвэрлэж таслана.
            clearSession();
            router.replace("/login");
        }
    }, [isLoading, user, router]);

    if (isLoading || !user) {
        return <main className="admin-shell admin-shell-loading">Ачааллаж байна...</main>;
    }

    return (
        <main className="admin-shell">
            <Sidebar user={user} />
            <section className="dashboard">{children}</section>
        </main>
    );
}
