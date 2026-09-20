"use client";

import { useEffect, useState } from "react";
import { api, CHANGE_TYPE_LABELS, type ChangeType, type CustomerData, type RecentCustomerChange, type UploadResult } from "@/lib/api";
import { PageHeader } from "@/components/layout/PageHeader";
import { Notice } from "@/components/ui/Notice";
import { useAuth } from "@/contexts/AuthContext";
import { useAsyncAction } from "@/hooks/useAsyncAction";

function customerLabel(customer: CustomerData) {
    return customer.o_c_customer_name || customer.c_lastname || customer.o_c_regnum || "Тодорхойгүй";
}

/** Мөрийн давтагдашгүй түлхүүр: нэг харилцагч өөр төрлөөр давхар орж болох тул төрөл + данс. */
function changeKey(change: RecentCustomerChange, index: number) {
    return `${change.changeType}:${change.accountId ?? change.clientId ?? index}`;
}

/** "Бүгд" + өөрчлөлтийн төрлүүд. Нэг дуудалтаар татсан жагсаалтыг эндээс шүүнэ. */
type RecentFilter = "ALL" | ChangeType;

const RECENT_FILTERS: RecentFilter[] = ["ALL", "NEW_LOAN", "CLOSED_LOAN", "REPAYMENT"];

const FILTER_LABELS: Record<RecentFilter, string> = {
    ALL: "Бүгд",
    ...CHANGE_TYPE_LABELS,
};

export default function CitizenUploadPage() {
    const { user } = useAuth();
    const [recentFilter, setRecentFilter] = useState<RecentFilter>("ALL");
    const [recentChanges, setRecentChanges] = useState<RecentCustomerChange[]>([]);
    const [selectedRecent, setSelectedRecent] = useState<string[]>([]);
    const [recentResults, setRecentResults] = useState<Record<string, UploadResult>>({});
    const [clientIdsInput, setClientIdsInput] = useState("");
    const [customers, setCustomers] = useState<CustomerData[]>([]);
    const [selected, setSelected] = useState<string[]>([]);
    const [results, setResults] = useState<Record<string, UploadResult>>({});
    const { busy: recentBusy, message: recentMessage, isError: recentIsError, setMessage: setRecentMessage, run: runRecent } = useAsyncAction();
    const { busy, message, isError, setMessage, run } = useAsyncAction();

    // Гурван төрлийг нэг дуудалтаар татаж, шүүлтүүрийг клиент талд хийнэ.
    const loadRecentChanges = () =>
        void runRecent(async () => {
            const found = await api.recentChanges();
            setRecentChanges(found);
            setSelectedRecent(found.map((change, index) => changeKey(change, index)));
            setRecentResults({});
            const counts = found.reduce((acc, change) => {
                acc[change.changeType] = (acc[change.changeType] ?? 0) + 1;
                return acc;
            }, {} as Record<string, number>);
            setRecentMessage(
                `Нийт ${found.length} өөрчлөлт: шинэ зээл ${counts.NEW_LOAN ?? 0}, зээл хаах ${counts.CLOSED_LOAN ?? 0}, эргэн төлөлт ${counts.REPAYMENT ?? 0}.`
            );
        });

    useEffect(() => {
        loadRecentChanges();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const visibleChanges = recentChanges
        .map((change, index) => ({ change, key: changeKey(change, index) }))
        .filter(({ change }) => recentFilter === "ALL" || change.changeType === recentFilter);

    const toggleRecent = (key: string) =>
        setSelectedRecent((current) => (current.includes(key) ? current.filter((id) => id !== key) : [...current, key]));

    const uploadRecentSelected = () =>
        void runRecent(async () => {
            const toUpload = recentChanges
                .map((change, index) => ({ change, key: changeKey(change, index) }))
                .filter(({ key }) => selectedRecent.includes(key));
            if (toUpload.length === 0) return;
            const uploadResults = await api.uploadRecentChanges(
                toUpload.map(({ change }) => ({ clientId: change.clientId, accountId: change.accountId, changeType: change.changeType })),
                user?.userId
            );
            setRecentResults((current) => {
                const next = { ...current };
                toUpload.forEach(({ key }, index) => {
                    next[key] = uploadResults[index];
                });
                return next;
            });
            const successCount = uploadResults.filter((item) => item.success).length;
            // Амжилттай илгээгдсэн мөрүүд сервер талд бүртгэгдэж, жагсаалтаас хасагддаг тул
            // үлдсэн мөрүүдийг харуулахын тулд дахин татна.
            if (successCount > 0) {
                const remaining = await api.recentChanges();
                setRecentChanges(remaining);
                setSelectedRecent(remaining.map((change, index) => changeKey(change, index)));
            }
            setRecentMessage(
                successCount === uploadResults.length
                    ? `${successCount} харилцагчийн мэдээллийг Сайн систем рүү амжилттай илгээж, жагсаалтаас хаслаа.`
                    : `${successCount}/${uploadResults.length} мөр амжилттай. Амжилтгүй мөрүүд жагсаалтад үлдэнэ.`
            );
        });

    const fetchCustomers = () =>
        void run(async () => {
            const clientIds = clientIdsInput
                .split(",")
                .map((value) => value.trim())
                .filter(Boolean);
            if (clientIds.length === 0) {
                setMessage("Хайх харилцагчийн дугаарыг таслалаар ялгаж оруулна уу.");
                return;
            }
            const found = await api.customersForClients(clientIds);
            setCustomers(found);
            setSelected(found.map((customer) => customer.o_c_regnum ?? "").filter(Boolean));
            setResults({});
            setMessage(`${found.length} харилцагчийн мэдээлэл ачааллаа.`);
        });

    const uploadSelected = () =>
        void run(async () => {
            const toUpload = customers.filter((customer) => selected.includes(customer.o_c_regnum ?? ""));
            if (toUpload.length === 0) return;
            const uploadResults = await api.uploadCitizen(toUpload);
            setResults((current) => {
                const next = { ...current };
                toUpload.forEach((customer, index) => {
                    const regnum = customer.o_c_regnum ?? "";
                    if (regnum) next[regnum] = uploadResults[index];
                });
                return next;
            });
            setMessage(
                uploadResults.every((item) => item.success)
                    ? `${uploadResults.length} харилцагчийн мэдээллийг Сайн систем рүү амжилттай илгээлээ.`
                    : "Зарим мэдээллийг илгээх явцад алдаа гарлаа."
            );
        });

    const toggle = (regnum: string) =>
        setSelected((current) => (current.includes(regnum) ? current.filter((id) => id !== regnum) : [...current, regnum]));

    return (
        <>
            <PageHeader breadcrumb="АДМИН / ИРГЭНИЙ МЭДЭЭЛЭЛ" title="Иргэний мэдээлэл, Сайн систем рүү илгээх" />

            <section className="inventory-panel">
                <div className="tab-bar" role="tablist" aria-label="Өөрчлөлтийн төрөл">
                    {RECENT_FILTERS.map((filter) => {
                        const count = filter === "ALL"
                            ? recentChanges.length
                            : recentChanges.filter((change) => change.changeType === filter).length;
                        return (
                            <button
                                key={filter}
                                type="button"
                                role="tab"
                                aria-selected={recentFilter === filter}
                                className={`tab-button${recentFilter === filter ? " active" : ""}`}
                                onClick={() => setRecentFilter(filter)}
                            >
                                {FILTER_LABELS[filter]} ({count})
                            </button>
                        );
                    })}
                </div>
                <div className="panel-toolbar">
                    <div>
                        <h3>Нийлүүлэх мэдээлэл</h3>
                        <p>Сүүлийн хугацаанд үүссэн шинэ зээл, зээл хаах, эргэн төлөлтийн жагсаалт (/api/send-data)</p>
                    </div>
                    <button className="outline-button" onClick={loadRecentChanges} disabled={recentBusy}>
                        Шинэчлэх
                    </button>
                </div>
                <div className="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th />
                                <th>№</th>
                                <th>Төрөл</th>
                                <th>Нэр</th>
                                <th>Регистрийн дугаар</th>
                                <th>Утас</th>
                                <th>Зээлийн үлдэгдэл</th>
                                <th>Илгээлтийн үр дүн</th>
                            </tr>
                        </thead>
                        <tbody>
                            {visibleChanges.length > 0 ? (
                                visibleChanges.map(({ change, key }, index) => {
                                    const result = recentResults[key];
                                    return (
                                        <tr key={key}>
                                            <td>
                                                <input
                                                    className="row-checkbox"
                                                    type="checkbox"
                                                    checked={selectedRecent.includes(key)}
                                                    onChange={() => toggleRecent(key)}
                                                    aria-label={`${change.customerName ?? change.accountId} сонгох`}
                                                />
                                            </td>
                                            <td>{index + 1}</td>
                                            <td>
                                                <span className={`change-tag change-${change.changeType.toLowerCase()}`}>
                                                    {CHANGE_TYPE_LABELS[change.changeType] ?? change.changeType}
                                                </span>
                                            </td>
                                            <td>{change.customerName ?? "—"}</td>
                                            <td>{change.regnum ?? "—"}</td>
                                            <td>{change.phone ?? "—"}</td>
                                            <td className="amount">{Number(change.balance ?? 0).toLocaleString("en-US")}</td>
                                            <td>
                                                {result ? (
                                                    <span className={`status${result.success ? "" : " status-muted"}`}>
                                                        {result.success ? "АМЖИЛТТАЙ" : (result.errors?.join(", ") ?? "АЛДАА")}
                                                    </span>
                                                ) : (
                                                    "—"
                                                )}
                                            </td>
                                        </tr>
                                    );
                                })
                            ) : (
                                <tr><td className="empty-state" colSpan={8}>{recentBusy ? "Ачааллаж байна..." : "Сүүлийн хугацаанд өөрчлөлт алга."}</td></tr>
                            )}
                        </tbody>
                    </table>
                </div>
                <div className="transfer-panel">
                    <p>Сонгосон {selectedRecent.length} харилцагчийн мэдээллийг Сайн систем рүү илгээнэ.</p>
                    <button className="primary-button" onClick={uploadRecentSelected} disabled={recentBusy || selectedRecent.length === 0}>
                        {recentBusy ? "Илгээж байна..." : "Сонгосныг илгээх"}
                    </button>
                </div>
                <Notice message={recentMessage} tone={recentIsError ? "error" : "info"} />
            </section>

            <section className="inventory-panel citizen-fetch-panel">
                <div className="panel-toolbar">
                    <div>
                        <h3>Харилцагчийн мэдээлэл татах</h3>
                        <p>Харилцагчийн дугааруудыг таслалаар ялгаж оруулна уу</p>
                    </div>
                    <div className="toolbar-actions">
                        <label className="search-field">
                            <span>ХАРИЛЦАГЧИЙН ДУГААР</span>
                            <input
                                value={clientIdsInput}
                                onChange={(event) => setClientIdsInput(event.target.value)}
                                placeholder="100238, 100411"
                            />
                        </label>
                        <button className="primary-button" onClick={fetchCustomers} disabled={busy}>
                            Татах
                        </button>
                    </div>
                </div>
                <div className="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th />
                                <th>Нэр</th>
                                <th>Регистр</th>
                                <th>Утас</th>
                                <th>Зээлийн үлдэгдэл</th>
                                <th>Илгээлтийн үр дүн</th>
                            </tr>
                        </thead>
                        <tbody>
                            {customers.length > 0 ? (
                                customers.map((customer, index) => {
                                    const regnum = customer.o_c_regnum ?? String(index);
                                    const result = results[regnum];
                                    return (
                                        <tr key={regnum}>
                                            <td>
                                                <input
                                                    className="row-checkbox"
                                                    type="checkbox"
                                                    checked={selected.includes(regnum)}
                                                    onChange={() => toggle(regnum)}
                                                    aria-label={`${regnum} сонгох`}
                                                />
                                            </td>
                                            <td>{customerLabel(customer)}</td>
                                            <td>{regnum}</td>
                                            <td>{customer.o_c_phone ?? "—"}</td>
                                            <td className="amount">{Number(customer.o_c_loan_information?.[0]?.o_c_loan_balance_lcy ?? 0).toLocaleString("en-US")}</td>
                                            <td>
                                                {result ? (
                                                    <span className={`status${result.success ? "" : " status-muted"}`}>
                                                        {result.success ? "АМЖИЛТТАЙ" : (result.errors?.join(", ") ?? "АЛДАА")}
                                                    </span>
                                                ) : (
                                                    "—"
                                                )}
                                            </td>
                                        </tr>
                                    );
                                })
                            ) : (
                                <tr><td className="empty-state" colSpan={6}>Харилцагчийн дугаараар хайлт хийнэ үү.</td></tr>
                            )}
                        </tbody>
                    </table>
                </div>
                <div className="transfer-panel">
                    <p>Сонгосон {selected.length} харилцагчийн мэдээллийг Сайн систем рүү илгээнэ.</p>
                    <button className="primary-button" onClick={uploadSelected} disabled={busy || selected.length === 0}>
                        {busy ? "Илгээж байна..." : "Сонгосныг илгээх"}
                    </button>
                </div>
            </section>

            <Notice message={message} tone={isError ? "error" : "info"} />
        </>
    );
}
