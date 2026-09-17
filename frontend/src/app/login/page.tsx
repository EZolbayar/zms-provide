"use client";

import { useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import { useAuth } from "@/contexts/AuthContext";

export default function LoginPage() {
    const { signIn } = useAuth();
    const router = useRouter();
    const searchParams = useSearchParams();
    const [userId, setUserId] = useState("");
    const [password, setPassword] = useState("");
    const [busy, setBusy] = useState(false);
    const [error, setError] = useState("");

    async function handleSubmit(event: React.FormEvent) {
        event.preventDefault();
        setBusy(true);
        setError("");
        try {
            await signIn(userId, password);
            router.replace(searchParams.get("from") || "/dashboard");
        } catch (err) {
            setError(err instanceof Error ? err.message : "Нэвтрэхэд алдаа гарлаа.");
        } finally {
            setBusy(false);
        }
    }

    return (
        <main className="login-shell">
            <div className="login-card">
                <div className="brand">
                    <span className="brand-mark">T</span>
                    <span>Тэргүүн ембүү ББСБ</span>
                </div>
                <h1>Тавтай морил</h1>
                <p className="login-subtitle">Дансны бүртгэл, харилцагчийн мэдээллийг удирдах системд нэвтэрнэ үү.</p>
                <form onSubmit={handleSubmit}>
                    <label>
                        <span>Хэрэглэгчийн нэр</span>
                        <input value={userId} onChange={(event) => setUserId(event.target.value)} placeholder="admin" autoFocus required />
                    </label>
                    <label>
                        <span>Нууц үг</span>
                        <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} placeholder="••••••••" required />
                    </label>
                    <button type="submit" className="primary-button" disabled={busy}>
                        {busy ? "Нэвтэрч байна..." : "Нэвтрэх"}
                    </button>
                </form>
                {error && <p className="notice error">{error}</p>}
                <p className="login-hint">Демо орчинд <strong>admin</strong> / хамаагүй нууц үг ашиглан нэвтэрч болно.</p>
            </div>
        </main>
    );
}
