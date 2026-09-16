"use client";

import Link from "next/link";
import { useEffect, useState } from "react";
import { api, type Account } from "@/lib/api";
import { PageHeader } from "@/components/layout/PageHeader";
import { StatCard } from "@/components/ui/StatCard";
import { StatusBadge } from "@/components/ui/StatusBadge";
import { Notice } from "@/components/ui/Notice";
import { useAsyncAction } from "@/hooks/useAsyncAction";

export default function DashboardPage() {
    const [accounts, setAccounts] = useState<Account[]>([]);
    const { busy, message, isError, run } = useAsyncAction();

    useEffect(() => {
        void run(async () => {
            setAccounts(await api.accounts());
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const activeAccounts = accounts.filter((account) => account.accountStatus === "ACTIVE");
    const totalBalance = activeAccounts.reduce((sum, account) => sum + Number(account.balance ?? 0), 0);
    const recentAccounts = [...accounts]
        .sort((a, b) => (a.openDate < b.openDate ? 1 : -1))
        .slice(0, 5);

    return (
        <>
            <PageHeader breadcrumb="АДМИН / ХЯНАХ САМБАР" title="Ерөнхий тойм" />

            <section className="summary-grid" aria-label="Ерөнхий үзүүлэлт">
                <StatCard label="Нийт данс" value={busy ? "…" : accounts.length} hint="Бүртгэлтэй нийт данс" />
                <StatCard label="Идэвхтэй данс" value={busy ? "…" : activeAccounts.length} hint="Боловсруулах боломжтой" />
                <StatCard label="Идэвхтэй үлдэгдэл" value={busy ? "…" : totalBalance.toLocaleString("en-US")} hint="Нийт идэвхтэй үлдэгдэл (₮)" />
            </section>

            <section className="inventory-panel">
                <div className="panel-toolbar">
                    <div>
                        <h3>Сүүлд нээгдсэн дансууд</h3>
                        <p>Хамгийн сүүлд бүртгэгдсэн 5 данс</p>
                    </div>
                    <Link href="/accounts" className="outline-button">
                        Бүх дансыг харах
                    </Link>
                </div>
                <div className="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>Дансны дугаар</th>
                                <th>Харилцагч</th>
                                <th>Бүтээгдэхүүн</th>
                                <th>Төлөв</th>
                                <th className="amount">Үлдэгдэл</th>
                                <th>Нээсэн огноо</th>
                            </tr>
                        </thead>
                        <tbody>
                            {recentAccounts.length > 0 ? (
                                recentAccounts.map((account) => (
                                    <tr key={account.accountId}>
                                        <td className="account-id">{account.accountId}</td>
                                        <td>{account.clientId}</td>
                                        <td>{account.productId}</td>
                                        <td><StatusBadge status={account.accountStatus} /></td>
                                        <td className="amount">{Number(account.balance ?? 0).toLocaleString("en-US")}</td>
                                        <td>{account.openDate}</td>
                                    </tr>
                                ))
                            ) : (
                                <tr><td className="empty-state" colSpan={6}>{busy ? "Ачааллаж байна..." : "Бүртгэл олдсонгүй."}</td></tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </section>

            <Notice message={message} tone={isError ? "error" : "info"} />
        </>
    );
}
