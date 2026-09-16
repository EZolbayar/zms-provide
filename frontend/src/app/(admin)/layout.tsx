"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { useAuth } from "@/contexts/AuthContext";
import { Sidebar } from "@/components/layout/Sidebar";

export default function AdminLayout({ children }: { children: React.ReactNode }) {
    const { user, isLoading } = useAuth();
    const router = useRouter();

    useEffect(() => {
        if (!isLoading && !user) router.replace("/login");
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
