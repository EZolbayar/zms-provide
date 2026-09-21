"use client";

import { useEffect, useState } from "react";
import { api, CHANGE_TYPE_LABELS, CLIENT_TYPE_LEGAL_ENTITY, type UploadLog } from "@/lib/api";
import { PageHeader } from "@/components/layout/PageHeader";
import { StatCard } from "@/components/ui/StatCard";
import { Notice } from "@/components/ui/Notice";
import { useAsyncAction } from "@/hooks/useAsyncAction";

const PAGE_SIZE = 20;

type ResultFilter = "ALL" | "SUCCESS" | "FAILED";

const RESULT_FILTERS: ResultFilter[] = ["ALL", "SUCCESS", "FAILED"];

const RESULT_LABELS: Record<ResultFilter, string> = {
    ALL: "Бүгд",
    SUCCESS: "Амжилттай",
    FAILED: "Амжилтгүй",
};

/** YYYY-MM-DD, хэрэглэгчийн орон нутгийн цагаар. */
function toDateInput(date: Date) {
    const local = new Date(date.getTime() - date.getTimezoneOffset() * 60000);
    return local.toISOString().slice(0, 10);
}

function formatDateTime(value?: string) {
    return value ? value.replace("T", " ").slice(0, 19) : "—";
}

function matchesFilter(log: UploadLog, filter: ResultFilter) {
    if (filter === "SUCCESS") return log.success === true;
    if (filter === "FAILED") return log.success !== true;
    return true;
}

export default function UploadLogsPage() {
    // Анхдагч хугацаа: сүүлийн 7 хоног (өнөөдрийг оруулаад).
    const [from, setFrom] = useState(() => toDateInput(new Date(Date.now() - 6 * 24 * 60 * 60 * 1000)));
    const [to, setTo] = useState(() => toDateInput(new Date()));
    const [logs, setLogs] = useState<UploadLog[]>([]);
    const [filter, setFilter] = useState<ResultFilter>("ALL");
    const [search, setSearch] = useState("");
    const [page, setPage] = useState(1);
    const { busy, message, isError, setMessage, run } = useAsyncAction();

    const loadLogs = (announce: boolean) =>
        void run(async () => {
            const result = await api.uploadLogs(from, to);
            setLogs(result);
            setPage(1);
            if (announce) setMessage(`${result.length} илгээлтийн бүртгэл ачааллаа.`);
        });

    useEffect(() => {
        loadLogs(false);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const query = search.trim().toLowerCase();
    const visibleLogs = logs
        .filter((log) => matchesFilter(log, filter))
        .filter(
            (log) =>
                !query ||
                [log.clientId, log.customerName, log.accountId, log.errorMessage, log.uploadedBy].some((value) =>
                    (value ?? "").toLowerCase().includes(query),
                ),
        );
    const totalPages = Math.max(1, Math.ceil(visibleLogs.length / PAGE_SIZE));
    const currentPage = Math.min(page, totalPages);
    const pagedLogs = visibleLogs.slice((currentPage - 1) * PAGE_SIZE, currentPage * PAGE_SIZE);
    const successCount = logs.filter((log) => log.success === true).length;

    return (
        <>
            <PageHeader breadcrumb="АДМИН / ИЛГЭЭЛТИЙН ЛОГ" title="Сайн систем рүү илгээсэн түүх" />

            <section className="summary-grid" aria-label="Илгээлтийн товч мэдээлэл">
                <StatCard label="Нийт илгээлт" value={logs.length} hint="Сонгосон хугацаанд" />
                <StatCard label="Амжилттай" value={successCount} hint="ЗМС хүлээн авсан" />
                <StatCard label="Амжилтгүй" value={logs.length - successCount} hint="Алдааг засаад дахин илгээнэ" />
            </section>

            <section className="inventory-panel">
                <div className="tab-bar" role="tablist" aria-label="Илгээлтийн үр дүн">
                    {RESULT_FILTERS.map((item) => (
                        <button
                            key={item}
                            type="button"
                            role="tab"
                            aria-selected={filter === item}
                            className={`tab-button${filter === item ? " active" : ""}`}
                            onClick={() => {
                                setFilter(item);
                                setPage(1);
                            }}
                        >
                            {RESULT_LABELS[item]} ({logs.filter((log) => matchesFilter(log, item)).length})
                        </button>
                    ))}
                </div>
                <div className="panel-toolbar">
                    <div>
                        <h3>Илгээлтийн лог</h3>
                        <p>Амжилтгүй илгээлт &quot;Нийлүүлэх мэдээлэл&quot; жагсаалтад үлдэх тул тэндээс дахин илгээнэ</p>
                    </div>
                    <div className="toolbar-actions">
                        <label className="search-field date-field">
                            <span>ЭХЛЭХ ОГНОО</span>
                            <input type="date" value={from} max={to} onChange={(event) => setFrom(event.target.value)} />
                        </label>
                        <label className="search-field date-field">
                            <span>ДУУСАХ ОГНОО</span>
                            <input type="date" value={to} min={from} onChange={(event) => setTo(event.target.value)} />
                        </label>
                        <label className="search-field">
                            <span>ХАЙХ</span>
                            <input
                                value={search}
                                onChange={(event) => {
                                    setSearch(event.target.value);
                                    setPage(1);
                                }}
                                placeholder="Харилцагч, данс, алдаа..."
                            />
                        </label>
                        <button className="outline-button" onClick={() => loadLogs(true)} disabled={busy}>
                            Шүүх
                        </button>
                    </div>
                </div>
                <div className="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>№</th>
                                <th>Огноо</th>
                                <th>Харилцагч</th>
                                <th>Данс</th>
                                <th>Төрөл</th>
                                <th>Үр дүн</th>
                                <th>Алдааны мэдээлэл</th>
                                <th>Илгээсэн</th>
                            </tr>
                        </thead>
                        <tbody>
                            {pagedLogs.length > 0 ? (
                                pagedLogs.map((log, index) => (
                                    <tr key={log.logId}>
                                        <td>{(currentPage - 1) * PAGE_SIZE + index + 1}</td>
                                        <td>{formatDateTime(log.uploadedOn)}</td>
                                        <td>
                                            {log.clientId ?? "—"}
                                            {log.customerName ? ` · ${log.customerName}` : ""}
                                            {log.customerType === CLIENT_TYPE_LEGAL_ENTITY ? " (ААН)" : ""}
                                        </td>
                                        <td className="account-id">{log.accountId ?? "—"}</td>
                                        <td>{log.changeType ? (CHANGE_TYPE_LABELS[log.changeType] ?? log.changeType) : "—"}</td>
                                        <td>
                                            <span className={`status${log.success ? "" : " status-error"}`}>
                                                {log.success ? "АМЖИЛТТАЙ" : "АМЖИЛТГҮЙ"}
                                            </span>
                                        </td>
                                        <td className="log-error">{log.success ? "" : (log.errorMessage ?? "—")}</td>
                                        <td>{log.uploadedBy ?? "—"}</td>
                                    </tr>
                                ))
                            ) : (
                                <tr><td className="empty-state" colSpan={8}>Сонгосон хугацаанд илгээлт олдсонгүй.</td></tr>
                            )}
                        </tbody>
                    </table>
                </div>
                {visibleLogs.length > 0 && (
                    <div className="pagination">
                        <span>
                            Нийт {visibleLogs.length} бүртгэлээс {(currentPage - 1) * PAGE_SIZE + 1}-{Math.min(currentPage * PAGE_SIZE, visibleLogs.length)}-г харуулж байна
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
