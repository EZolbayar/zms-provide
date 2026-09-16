"use client";

import { useEffect, useState } from "react";
import { api, type Account } from "@/lib/api";
import { PageHeader } from "@/components/layout/PageHeader";
import { StatCard } from "@/components/ui/StatCard";
import { StatusBadge } from "@/components/ui/StatusBadge";
import { Notice } from "@/components/ui/Notice";
import { useAsyncAction } from "@/hooks/useAsyncAction";

export default function AccountsPage() {
    const [accounts, setAccounts] = useState<Account[]>([]);
    const [selectedClientIds, setSelectedClientIds] = useState<string[]>([]);
    const [search, setSearch] = useState("");
    const { busy, message, isError, setMessage, run } = useAsyncAction();

    const loadAccounts = () =>
        void run(async () => {
            const result = await api.accounts();
            setAccounts(result);
            setMessage(`${result.length} дансны мэдээлэл ачааллаа.`);
        });

    const uploadSelectedCustomers = () =>
        void run(async () => {
            if (selectedClientIds.length === 0) return;
            const customers = await api.customersForClients(selectedClientIds);
            const result = await api.uploadCitizen(customers);
            setMessage(
                result.every((item) => item.success)
                    ? `${result.length} харилцагчийн мэдээллийг амжилттай илгээлээ.`
                    : "Зарим мэдээллийг илгээх явцад алдаа гарлаа."
            );
            if (result.every((item) => item.success)) setSelectedClientIds([]);
        });

    useEffect(() => {
        void run(async () => {
            setAccounts(await api.accounts());
        });
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const visibleAccounts = accounts.filter((account) =>
        [account.accountId, account.clientId, account.productId, account.accountType, account.accountStatus].some((value) =>
            value.toLowerCase().includes(search.toLowerCase())
        )
    );
    const visibleClientIds = [...new Set(visibleAccounts.map((account) => account.clientId))];
    const allVisibleSelected = visibleClientIds.length > 0 && visibleClientIds.every((clientId) => selectedClientIds.includes(clientId));
    const toggleClient = (clientId: string) =>
        setSelectedClientIds((selected) => (selected.includes(clientId) ? selected.filter((id) => id !== clientId) : [...selected, clientId]));
    const toggleAllVisible = () =>
        setSelectedClientIds((selected) => (allVisibleSelected ? selected.filter((id) => !visibleClientIds.includes(id)) : [...new Set([...selected, ...visibleClientIds])]));

    return (
        <>
            <PageHeader breadcrumb="АДМИН / ДАНСНЫ БҮРТГЭЛ" title="Дансны удирдлага" />

            <section className="page-heading">
                <div>
                    <h2>Бүртгэлтэй дансууд</h2>
                    <p>Сонгосон харилцагчийн мэдээллийг Сайн систем рүү багцаар илгээнэ.</p>
                </div>
                <button className="primary-button" onClick={uploadSelectedCustomers} disabled={busy || selectedClientIds.length === 0}>
                    {busy ? "Илгээж байна..." : `Сонгосон ${selectedClientIds.length} харилцагчийг илгээх`}
                </button>
            </section>

            <section className="summary-grid" aria-label="Дансны товч мэдээлэл">
                <StatCard label="Нийт данс" value={accounts.length} hint="Ачаалсан бүртгэл" />
                <StatCard label="Идэвхтэй данс" value={accounts.filter((account) => account.accountStatus === "ACTIVE").length} hint="Боловсруулах боломжтой" />
                <StatCard label="Сонгосон харилцагч" value={selectedClientIds.length} hint="Багцаар илгээхэд бэлэн" />
            </section>

            <section className="inventory-panel">
                <div className="panel-toolbar">
                    <div>
                        <h3>Дансны жагсаалт</h3>
                        <p>ZMS-д бүртгэлтэй бүх данс</p>
                    </div>
                    <div className="toolbar-actions">
                        <label className="search-field">
                            <span>ХАЙХ</span>
                            <input value={search} onChange={(event) => setSearch(event.target.value)} placeholder="Данс, харилцагч, бүтээгдэхүүн..." />
                        </label>
                        <button className="outline-button" onClick={loadAccounts} disabled={busy}>
                            Шинэчлэх
                        </button>
                    </div>
                </div>
                <div className="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>
                                    <input className="row-checkbox" type="checkbox" checked={allVisibleSelected} onChange={toggleAllVisible} aria-label="Харагдаж буй бүх харилцагчийг сонгох" />
                                </th>
                                <th>Дансны дугаар</th>
                                <th>Харилцагч</th>
                                <th>Бүтээгдэхүүн</th>
                                <th>Төрөл</th>
                                <th>Төлөв</th>
                                <th className="amount">Үлдэгдэл</th>
                                <th>Нээсэн огноо</th>
                            </tr>
                        </thead>
                        <tbody>
                            {visibleAccounts.length > 0 ? (
                                visibleAccounts.map((account) => (
                                    <tr key={account.accountId}>
                                        <td>
                                            <input
                                                className="row-checkbox"
                                                type="checkbox"
                                                checked={selectedClientIds.includes(account.clientId)}
                                                onChange={() => toggleClient(account.clientId)}
                                                aria-label={`${account.clientId} харилцагчийг сонгох`}
                                            />
                                        </td>
                                        <td className="account-id">{account.accountId}</td>
                                        <td>{account.clientId}</td>
                                        <td>{account.productId}</td>
                                        <td>{account.accountType}</td>
                                        <td><StatusBadge status={account.accountStatus} /></td>
                                        <td className="amount">{Number(account.balance ?? 0).toLocaleString("en-US")}</td>
                                        <td>{account.openDate}</td>
                                    </tr>
                                ))
                            ) : (
                                <tr><td className="empty-state" colSpan={8}>Хайлтад тохирох бүртгэл олдсонгүй.</td></tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </section>

            <Notice message={message} tone={isError ? "error" : "info"} />
        </>
    );
}
