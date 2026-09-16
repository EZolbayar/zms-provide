export function StatCard({ label, value, hint, accent }: { label: string; value: string | number; hint: string; accent?: "aqua" | "amber" }) {
    return (
        <div className={accent ? `accent-${accent}` : undefined}>
            <span>{label}</span>
            <strong>{value}</strong>
            <small>{hint}</small>
        </div>
    );
}
