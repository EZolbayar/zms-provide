export function StatusBadge({ status }: { status: string }) {
    const label = status === "ACTIVE" ? "ИДЭВХТЭЙ" : status === "CLOSED" ? "ХААГДСАН" : status;
    return <span className={`status${status === "ACTIVE" ? "" : " status-muted"}`}>{label}</span>;
}
