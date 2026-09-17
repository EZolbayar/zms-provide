"use client";

import { useEffect, useState } from "react";
import { api, type CustomerData, type UploadResult } from "@/lib/api";
import { PageHeader } from "@/components/layout/PageHeader";
import { Notice } from "@/components/ui/Notice";
import { useAsyncAction } from "@/hooks/useAsyncAction";

function customerLabel(customer: CustomerData) {
    return customer.o_c_customer_name || customer.c_lastname || customer.o_c_regnum || "Тодорхойгүй";
}

export default function CitizenUploadPage() {
    const [recentCustomers, setRecentCustomers] = useState<CustomerData[]>([]);
    const [selectedRecent, setSelectedRecent] = useState<string[]>([]);
    const [recentResults, setRecentResults] = useState<Record<string, UploadResult>>({});
    const [clientIdsInput, setClientIdsInput] = useState("");
    const [customers, setCustomers] = useState<CustomerData[]>([]);
    const [selected, setSelected] = useState<string[]>([]);
    const [results, setResults] = useState<Record<string, UploadResult>>({});
    const { busy: recentBusy, message: recentMessage, isError: recentIsError, setMessage: setRecentMessage, run: runRecent } = useAsyncAction();
    const { busy, message, isError, setMessage, run } = useAsyncAction();

    const loadRecentCustomers = () =>
        void runRecent(async () => {
            const found = await api.recentlyChangedCustomers();
            setRecentCustomers(found);
            setSelectedRecent(found.map((customer) => customer.o_c_regnum ?? "").filter(Boolean));
            setRecentResults({});
            setRecentMessage(`Сүүлийн 24 цагт өөрчлөгдсөн ${found.length} харилцагч олдлоо.`);
        });

    useEffect(() => {
        loadRecentCustomers();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    const toggleRecent = (regnum: string) =>
        setSelectedRecent((current) => (current.includes(regnum) ? current.filter((id) => id !== regnum) : [...current, regnum]));

    const uploadRecentSelected = () =>
        void runRecent(async () => {
            const toUpload = recentCustomers.filter((customer) => selectedRecent.includes(customer.o_c_regnum ?? ""));
            if (toUpload.length === 0) return;
            const uploadResults = await api.uploadCitizen(toUpload);
            setRecentResults((current) => {
                const next = { ...current };
                toUpload.forEach((customer, index) => {
                    const regnum = customer.o_c_regnum ?? "";
                    if (regnum) next[regnum] = uploadResults[index];
                });
                return next;
            });
            setRecentMessage(
                uploadResults.every((item) => item.success)
                    ? `${uploadResults.length} харилцагчийн мэдээллийг Сайн систем рүү амжилттай илгээлээ.`
                    : "Зарим мэдээллийг илгээх явцад алдаа гарлаа."
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
                <div className="panel-toolbar">
                    <div>
                        <h3>Сүүлийн 24 цагт өөрчлөгдсөн харилцагчид</h3>
                        <p>Дансны мэдээлэл өөрчлөгдсөн харилцагчдын жагсаалт (/api/send-data)</p>
                    </div>
                    <button className="outline-button" onClick={loadRecentCustomers} disabled={recentBusy}>
                        Шинэчлэх
                    </button>
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
                            {recentCustomers.length > 0 ? (
                                recentCustomers.map((customer, index) => {
                                    const regnum = customer.o_c_regnum ?? String(index);
                                    const result = recentResults[regnum];
                                    return (
                                        <tr key={regnum}>
                                            <td>
                                                <input
                                                    className="row-checkbox"
                                                    type="checkbox"
                                                    checked={selectedRecent.includes(regnum)}
                                                    onChange={() => toggleRecent(regnum)}
                                                    aria-label={`${regnum} сонгох`}
                                                />
                                            </td>
                                            <td>{customerLabel(customer)}</td>
                                            <td>{regnum}</td>
                                            <td>{customer.o_c_phone ?? "—"}</td>
                                            <td className="amount">{Number(customer.o_c_loan_information?.o_c_loan_balance_lcy ?? 0).toLocaleString("en-US")}</td>
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
                                <tr><td className="empty-state" colSpan={6}>{recentBusy ? "Ачааллаж байна..." : "Сүүлийн 24 цагт өөрчлөгдсөн харилцагч алга."}</td></tr>
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
                                placeholder="C-100238, C-100411"
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
                                            <td className="amount">{Number(customer.o_c_loan_information?.balanceLcy ?? 0).toLocaleString("en-US")}</td>
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
