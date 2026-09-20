"use client";

import { useEffect, useState } from "react";
import { api, type Account } from "@/lib/api";
import { PageHeader } from "@/components/layout/PageHeader";
import { StatCard } from "@/components/ui/StatCard";
import { StatusBadge } from "@/components/ui/StatusBadge";
import { Notice } from "@/components/ui/Notice";
import { useAsyncAction } from "@/hooks/useAsyncAction";

const PAGE_SIZE = 10;

export default function AccountsPage() {
    const [accounts, setAccounts] = useState<Account[]>([]);
    const [search, setSearch] = useState("");
    const [page, setPage] = useState(1);
    const { busy, message, isError, setMessage, run } = useAsyncAction();

    const loadAccounts = () =>
        void run(async () => {
            const result = await api.accounts();
            setAccounts(result);
            setMessage(`${result.length} дансны мэдээлэл ачааллаа.`);
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
    const totalPages = Math.max(1, Math.ceil(visibleAccounts.length / PAGE_SIZE));
    const currentPage = Math.min(page, totalPages);
    const pagedAccounts = visibleAccounts.slice((currentPage - 1) * PAGE_SIZE, currentPage * PAGE_SIZE);

    return (
        <>
            <PageHeader breadcrumb="АДМИН / ДАНСНЫ БҮРТГЭЛ" title="Дансны удирдлага" />

            <section className="summary-grid" aria-label="Дансны товч мэдээлэл">
                <StatCard label="Нийт данс" value={accounts.length} hint="Ачаалсан бүртгэл" />
                <StatCard label="Идэвхтэй данс" value={accounts.filter((account) => account.accountStatus === "A").length} hint="Боловсруулах боломжтой" />
            </section>

            <section className="inventory-panel">
                <div className="panel-toolbar">
                    <div>
                        <h3>Дансны жагсаалт</h3>
                        <p>Бүртгэлтэй бүх данс</p>
                    </div>
                    <div className="toolbar-actions">   
                        <label className="search-field">
                            <span>ХАЙХ</span>
                            <input
                                value={search}
                                onChange={(event) => {
                                    setSearch(event.target.value);
                                    setPage(1);
                                }}
                                placeholder="Данс, харилцагч, бүтээгдэхүүн..."
                            />
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
                            {pagedAccounts.length > 0 ? (
                                pagedAccounts.map((account) => (
                                    <tr key={account.accountId}>
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
                                <tr><td className="empty-state" colSpan={7}>Хайлтад тохирох бүртгэл олдсонгүй.</td></tr>
                            )}
                        </tbody>
                    </table>
                </div>
                {visibleAccounts.length > 0 && (
                    <div className="pagination">
                        <span>
                            Нийт {visibleAccounts.length} бүртгэлээс {(currentPage - 1) * PAGE_SIZE + 1}-{Math.min(currentPage * PAGE_SIZE, visibleAccounts.length)}-г харуулж байна
                        </span>
                        <div className="pagination-controls">
                            <button className="outline-button" onClick={() => setPage((p) => p - 1)} disabled={currentPage <= 1}>
                                Өмнөх
                            </button>
                            <span className="pagination-current">{currentPage} / {totalPages}</span>
                            <button className="outline-button" onClick={() => setPage((p) => p + 1)} disabled={currentPage >= totalPages}>
                                Дараах
                            </button>
                        </div>
                    </div>
                )}
            </section>

            <Notice message={message} tone={isError ? "error" : "info"} />
        </>
    );
}
