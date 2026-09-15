"use client";

import { useEffect, useState } from "react";
import { Account, api, UploadResult, usingDummyData } from "@/lib/api";

export default function Home() {
    const [accounts, setAccounts] = useState<Account[]>([]);
    const [uploadResults, setUploadResults] = useState<UploadResult[] | null>(null);
    const [selectedClientIds, setSelectedClientIds] = useState<string[]>([]);
    const [search, setSearch] = useState("");
    const [message, setMessage] = useState("");
    const [busy, setBusy] = useState(false);

    async function execute(action: () => Promise<void>) {
        setBusy(true);
        setMessage("");
        try { await action(); } catch (error) { setMessage(error instanceof Error ? error.message : "Хүсэлтийг гүйцэтгэж чадсангүй."); } finally { setBusy(false); }
    }

    const loadAccounts = () => void execute(async () => { const result = await api.accounts(); setAccounts(result); setMessage(`${result.length} дансны мэдээлэл ачааллаа.`); });
    const uploadSelectedCustomers = () => void execute(async () => {
        if (selectedClientIds.length === 0) return;
        const customers = await api.customersForClients(selectedClientIds);
        const result = await api.uploadCitizen(customers);
        setUploadResults(result);
        setMessage(result.every((item) => item.success) ? `${result.length} харилцагчийн мэдээллийг амжилттай илгээлээ.` : "Зарим мэдээллийг илгээх явцад алдаа гарлаа.");
        if (result.every((item) => item.success)) setSelectedClientIds([]);
    });

    useEffect(() => {
        void api.accounts().then(setAccounts).catch((error: unknown) => {
            setMessage(error instanceof Error ? error.message : "Хүсэлтийг гүйцэтгэж чадсангүй.");
        });
    }, []);

    const visibleAccounts = accounts.filter((account) => [account.accountId, account.clientId, account.productId, account.accountType, account.accountStatus].some((value) => value.toLowerCase().includes(search.toLowerCase())));
    const visibleClientIds = [...new Set(visibleAccounts.map((account) => account.clientId))];
    const allVisibleSelected = visibleClientIds.length > 0 && visibleClientIds.every((clientId) => selectedClientIds.includes(clientId));
    const toggleClient = (clientId: string) => setSelectedClientIds((selected) => selected.includes(clientId) ? selected.filter((id) => id !== clientId) : [...selected, clientId]);
    const toggleAllVisible = () => setSelectedClientIds((selected) => allVisibleSelected ? selected.filter((id) => !visibleClientIds.includes(id)) : [...new Set([...selected, ...visibleClientIds])]);

    return <main className="admin-shell">
        <aside className="sidebar">
            <div className="brand"><span className="brand-mark">Z</span><span>ZMS</span></div><p className="menu-label">УДИРДЛАГЫН САМБАР</p>
            <nav aria-label="Үндсэн цэс"><span className="nav-item active"><span className="nav-dot" />Дансны бүртгэл</span></nav>
            <div className="sidebar-footer"><span className="live-dot" />{usingDummyData ? "Демо орчин" : "API холбогдсон"}</div>
        </aside>
        <section className="dashboard">
            <header className="header"><div><p className="breadcrumb">АДМИН / ДАНСНЫ БҮРТГЭЛ</p><h1>Дансны удирдлага</h1></div><div className="user-menu"><span className="user-avatar">A</span><div><strong>Администратор</strong><small>Системийн оператор</small></div></div></header>
            <section className="page-heading"><div><h2>Бүртгэлтэй дансууд</h2><p>Сонгосон харилцагчийн мэдээллийг Сайн систем рүү багцаар илгээнэ.</p></div><button className="primary-button" onClick={uploadSelectedCustomers} disabled={busy || selectedClientIds.length === 0}>{busy ? "Илгээж байна..." : `Сонгосон ${selectedClientIds.length} харилцагчийг илгээх`}</button></section>
            <section className="summary-grid" aria-label="Дансны товч мэдээлэл"><div><span>Нийт данс</span><strong>{accounts.length}</strong><small>Ачаалсан бүртгэл</small></div><div><span>Идэвхтэй данс</span><strong>{accounts.filter((account) => account.accountStatus === "ACTIVE").length}</strong><small>Боловсруулах боломжтой</small></div><div><span>Сонгосон харилцагч</span><strong>{selectedClientIds.length}</strong><small>Багцаар илгээхэд бэлэн</small></div></section>
            <section className="inventory-panel"><div className="panel-toolbar"><div><h3>Дансны жагсаалт</h3><p>ZMS-д бүртгэлтэй бүх данс</p></div><div className="toolbar-actions"><label className="search-field"><span>ХАЙХ</span><input value={search} onChange={(event) => setSearch(event.target.value)} placeholder="Данс, харилцагч, бүтээгдэхүүн..." /></label><button className="outline-button" onClick={loadAccounts} disabled={busy}>Шинэчлэх</button></div></div><div className="table-wrap"><table><thead><tr><th><input className="row-checkbox" type="checkbox" checked={allVisibleSelected} onChange={toggleAllVisible} aria-label="Харагдаж буй бүх харилцагчийг сонгох" /></th><th>Дансны дугаар</th><th>Харилцагч</th><th>Бүтээгдэхүүн</th><th>Төрөл</th><th>Төлөв</th><th className="amount">Үлдэгдэл</th><th>Нээсэн огноо</th></tr></thead><tbody>{visibleAccounts.length > 0 ? visibleAccounts.map((account) => <tr key={account.accountId}><td><input className="row-checkbox" type="checkbox" checked={selectedClientIds.includes(account.clientId)} onChange={() => toggleClient(account.clientId)} aria-label={`${account.clientId} харилцагчийг сонгох`} /></td><td className="account-id">{account.accountId}</td><td>{account.clientId}</td><td>{account.productId}</td><td>{account.accountType}</td><td><span className="status">{account.accountStatus === "ACTIVE" ? "ИДЭВХТЭЙ" : account.accountStatus}</span></td><td className="amount">{Number(account.balance ?? 0).toLocaleString("en-US")}</td><td>{account.openDate}</td></tr>) : <tr><td className="empty-state" colSpan={8}>Хайлтад тохирох бүртгэл олдсонгүй.</td></tr>}</tbody></table></div></section>
            {(message || uploadResults) && <p className={`notice ${message.includes("чадсангүй") || message.includes("алдаа") ? "error" : ""}`}>{message || (uploadResults?.every((item) => item.success) ? "Илгээлт амжилттай дууслаа." : "Илгээлтэд анхаарал шаардлагатай.")}</p>}
        </section>
    </main>;
}