"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { usingDummyData } from "@/lib/api";
import type { Session } from "@/lib/auth/session";

type NavItem = {
    href: string;
    label: string;
    adminOnly?: boolean;
};

const NAV_ITEMS: NavItem[] = [
    { href: "/dashboard", label: "Хянах самбар" },
    { href: "/accounts", label: "Дансны бүртгэл" },
    { href: "/citizen-upload", label: "Иргэний мэдээлэл" },
];

export function Sidebar({ user }: { user: Session | null }) {
    const pathname = usePathname();

    return (
        <aside className="sidebar">
            <div className="brand">
                <span className="brand-mark">T</span>
                <span>TERGUUN</span>
            </div>
            <p className="menu-label">УДИРДЛАГЫН САМБАР</p>
            <nav aria-label="Үндсэн цэс">
                {NAV_ITEMS.filter((item) => !item.adminOnly || user?.isAdmin).map((item) => {
                    const active = pathname === item.href || pathname?.startsWith(`${item.href}/`);
                    return (
                        <Link key={item.href} href={item.href} className={`nav-item${active ? " active" : ""}`}>
                            <span className="nav-dot" />
                            {item.label}
                        </Link>
                    );
                })}
            </nav>
            <div className="sidebar-footer">
                <span className="live-dot" />
                {usingDummyData ? "Демо орчин" : "API холбогдсон"}
            </div>
        </aside>
    );
}
