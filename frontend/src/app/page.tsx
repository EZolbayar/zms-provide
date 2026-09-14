"use client";

import { FormEvent, useState } from "react";
import { Account, api, CustomerData, LoginResponse, UploadResult, usingDummyData } from "@/lib/api";

export default function Home() {
  const [user, setUser] = useState<LoginResponse | null>(null);
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");
  const [accounts, setAccounts] = useState<Account[]>([]);
  const [customer, setCustomer] = useState<CustomerData | null>(null);
  const [uploadResults, setUploadResults] = useState<UploadResult[] | null>(null);
  const [message, setMessage] = useState(usingDummyData ? "Демо горим идэвхтэй байна. Дурын эрхээр нэвтэрч үйл явцыг туршина уу." : "ZMS өгөгдөл дамжуулагч API-д нэвтэрнэ үү.");
  const [busy, setBusy] = useState(false);

  async function execute(action: () => Promise<void>) {
    setBusy(true);
    setMessage("");
    try {
      await action();
    } catch (error) {
      setMessage(error instanceof Error ? error.message : "Хүсэлтийг гүйцэтгэж чадсангүй.");
    } finally {
      setBusy(false);
    }
  }

  function handleLogin(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    void execute(async () => {
      const result = await api.login(userId, password);
      setUser(result);
      setMessage(`${result.userName || result.userId} хэрэглэгчээр нэвтэрлээ.`);
    });
  }

  const loadAccounts = () => void execute(async () => {
    const result = await api.accounts();
    setAccounts(result);
    setMessage(`${result.length} дансны мэдээлэл ачааллаа.`);
  });

  const prepareCustomer = () => void execute(async () => {
    const result = await api.latestCustomer();
    setCustomer(result);
    setUploadResults(null);
    setMessage(result ? "Сүүлд өөрчлөгдсөн харилцагчийн мэдээлэл хянахад бэлэн боллоо." : "Сүүлд өөрчлөгдсөн харилцагчийн мэдээлэл олдсонгүй.");
  });

  const uploadCustomer = () => void execute(async () => {
    if (!customer) return;
    const result = await api.uploadCitizen([customer]);
    setUploadResults(result);
    setMessage(result.every((item) => item.success) ? "Иргэний мэдээллийг амжилттай илгээлээ." : "Илгээх явцад алдаа гарлаа.");
  });

  const logout = () => void execute(async () => {
    if (user) await api.logout(user.userId);
    setUser(null);
    setAccounts([]);
    setCustomer(null);
    setUploadResults(null);
    setMessage("Системээс гарлаа.");
  });

  return (
    <main className="workspace">
      <header className="topbar">
        <div><p className="eyebrow">ZMS PROVIDER</p><h1>Илгээмжийн самбар</h1></div>
        <div className="api-indicator"><span /> {usingDummyData ? "ДЕМО ӨГӨГДӨЛ" : `API ${process.env.NEXT_PUBLIC_API_BASE_URL ?? "localhost:8081"}`}</div>
      </header>

      {!user ? (
        <section className="login-panel">
          <div><p className="eyebrow">АЖИЛТНЫ НЭВТРЭЛТ</p><h2>Өгөгдөл илгээх үйлдлээ эхлүүлэхийн тулд нэвтэрнэ үү.</h2></div>
          <form onSubmit={handleLogin}>
            <label>Хэрэглэгчийн нэр<input value={userId} onChange={(event) => setUserId(event.target.value)} required autoComplete="username" /></label>
            <label>Нууц үг<input type="password" value={password} onChange={(event) => setPassword(event.target.value)} required autoComplete="current-password" /></label>
            <button className="primary" disabled={busy}>{busy ? "Нэвтэрч байна..." : "Нэвтрэх"}</button>
          </form>
        </section>
      ) : (
        <section className="console">
          <div className="operator"><span className="avatar">{(user.userName || user.userId).slice(0, 1)}</span><div><strong>{user.userName || user.userId}</strong><small>{user.isAdmin ? "Администратор" : "Оператор"}</small></div><button className="quiet" onClick={logout} disabled={busy}>Гарах</button></div>
          <div className="action-grid">
            <section className="panel"><p className="eyebrow">01 / ДАНС</p><h2>Багцын бүртгэл</h2><p>Өгөгдөл дамжуулагчаас ашиглах боломжтой бүх дансны мэдээллийг авна.</p><button className="secondary" onClick={loadAccounts} disabled={busy}>Данс ачаалах</button></section>
            <section className="panel"><p className="eyebrow">02 / БЭЛТГЭХ</p><h2>Сүүлийн харилцагч</h2><p>Сүүлд өөрчлөгдсөн данснуудаас илгээх мэдээллийг бүрдүүлнэ.</p><button className="secondary" onClick={prepareCustomer} disabled={busy}>Илгээмж бэлтгэх</button></section>
            <section className="panel emphasis"><p className="eyebrow">03 / ИЛГЭЭХ</p><h2>Иргэний мэдээлэл</h2><p>Хянасан харилцагчийн мэдээллийг provider API-аар дамжуулан Sain руу илгээнэ.</p><button className="primary" onClick={uploadCustomer} disabled={busy || !customer}>Иргэний мэдээлэл илгээх</button></section>
          </div>

          {accounts.length > 0 && <section className="data-panel"><div className="panel-title"><h2>Дансууд</h2><span>{accounts.length} бүртгэл</span></div><div className="table-wrap"><table><thead><tr><th>Данс</th><th>Харилцагч</th><th>Бүтээгдэхүүн</th><th>Төлөв</th><th className="amount">Үлдэгдэл</th></tr></thead><tbody>{accounts.map((account) => <tr key={account.accountId}><td>{account.accountId}</td><td>{account.clientId}</td><td>{account.productId}</td><td><span className="status">{account.accountStatus}</span></td><td className="amount">{Number(account.balance ?? 0).toLocaleString()}</td></tr>)}</tbody></table></div></section>}

          {customer && <section className="data-panel"><div className="panel-title"><h2>Бэлтгэсэн мэдээлэл</h2><span>{String(customer.action ?? "add").toUpperCase()}</span></div><div className="customer-summary"><div><small>Харилцагч</small><strong>{String(customer.customerName ?? "Нэргүй харилцагч")}</strong></div><div><small>Регистрийн дугаар</small><strong>{String(customer.regnum ?? "Байхгүй")}</strong></div></div><pre>{JSON.stringify(customer, null, 2)}</pre></section>}
          {uploadResults && <section className="result"><strong>{uploadResults.every((item) => item.success) ? "Илгээмжийг хүлээн авлаа" : "Илгээмжид анхаарал шаардлагатай"}</strong>{uploadResults.map((item, index) => <p key={index}>{item.success ? "Амжилттай" : item.errors?.join(", ") || "Татгалзсан"}{item.action ? ` (${item.action})` : ""}</p>)}</section>}
        </section>
      )}
      <p className={`notice ${message.includes("Cannot reach") || message.includes("error") ? "error" : ""}`}>{message}</p>
    </main>
  );
}
