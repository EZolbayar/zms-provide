"use client";

import { useAuth } from "@/contexts/AuthContext";

export function UserMenu() {
    const { user, signOut } = useAuth();
    if (!user) return null;

    const initial = user.userName.trim().charAt(0).toUpperCase() || "?";

    return (
        <div className="user-menu">
            <span className="user-avatar">{initial}</span>
            <div>
                <strong>{user.userName}</strong>
                <small>{user.isAdmin ? "Системийн администратор" : "Оператор"}</small>
            </div>
            <button className="more-button logout-button" onClick={() => void signOut()} aria-label="Гарах" title="Гарах">
                ⎋
            </button>
        </div>
    );
}
